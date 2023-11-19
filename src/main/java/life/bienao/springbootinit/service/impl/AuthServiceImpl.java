package life.bienao.springbootinit.service.impl;

import com.alibaba.fastjson.JSONObject;
import life.bienao.springbootinit.constant.Redis;
import life.bienao.springbootinit.entity.LoginBody;
import life.bienao.springbootinit.entity.LoginUser;
import life.bienao.springbootinit.entity.SysUser;
import life.bienao.springbootinit.entity.SysUserRole;
import life.bienao.springbootinit.mapper.SysUserMapper;
import life.bienao.springbootinit.mapper.SysUserRoleMapper;
import life.bienao.springbootinit.service.AuthService;
import life.bienao.springbootinit.util.JwtUtil;
import life.bienao.springbootinit.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public JSONObject login(SysUser user) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登陆失败！");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getUserId().toString();
        String jwt = JwtUtil.createJWT(userId);
        JSONObject result = new JSONObject();
        result.put("token", "Bearer " + jwt);
        Redis.loginUser.put("login:" + userId, loginUser);
        return result;

    }

    @Override
    public void logout() {
        //获取SecurityContextHolder中的用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getUserId();
        //删除用户数据
        Redis.loginUser.remove("login:" + userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void registerUser(LoginBody loginBody) {
        //1、查询用户名是否存在
        SysUser sysUser = sysUserMapper.loadUserByUsername(loginBody.getUsername());
        if (sysUser != null){
            throw new RuntimeException("用户名已存在，请重新输入！");
        }
        //2、注册用户
        String username = loginBody.getUsername();
        String password = loginBody.getPassword();
        String bcryptPasswd = SecurityUtils.bCryptPasswordEncoder.encode(password);
        SysUser registerUser = new SysUser(username, username, bcryptPasswd);
        if (sysUserMapper.insertUser(registerUser) > 0){
            //3、获取到刚刚自增的id
            Long userId = registerUser.getUserId();
            //4、分配用户角色（默认注册时是普通成员）
            List<SysUserRole> userRoles = new ArrayList<>(1);
            userRoles.add(new SysUserRole(userId, 2L));
            sysUserRoleMapper.batchUserRole(userRoles);
        }else {
            throw new RuntimeException("注册失败！");
        }
    }
}
