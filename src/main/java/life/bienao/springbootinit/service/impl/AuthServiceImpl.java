package life.bienao.springbootinit.service.impl;

import com.alibaba.fastjson.JSONObject;
import life.bienao.springbootinit.constant.Redis;
import life.bienao.springbootinit.entity.*;
import life.bienao.springbootinit.mapper.SysUserMapper;
import life.bienao.springbootinit.mapper.SysUserRoleMapper;
import life.bienao.springbootinit.service.AuthService;
import life.bienao.springbootinit.service.SysMenuService;
import life.bienao.springbootinit.util.JwtUtil;
import life.bienao.springbootinit.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    protected BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private SysMenuService menuService;

    @Override
    public JSONObject login(SysUser user) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登陆失败！");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long userId = loginUser.getUser().getUserId();
        String jwt = JwtUtil.createJWT(userId.toString());
        JSONObject result = new JSONObject();
        result.put("token", "Bearer " + jwt);
        Redis.loginUser.put("login:" + userId, loginUser);
        result.put("user", loginUser);
        //获取菜单表中menu_type为M、C的菜单记录。取得的集合是已经进行父子表递归过的
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        //构建前端所需要的路由表
        List<RouterVo> routerVos = menuService.buildMenus(menus);
        result.put("routers", routerVos);
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
    public void registerUser(SysUser user) {
        //1、查询用户名是否存在
        SysUser sysUser = sysUserMapper.loadUserByUsername(user.getUserName());
        if (sysUser != null){
            throw new RuntimeException("用户名已存在，请重新输入！");
        }
        //2、注册用户
        String username = user.getUserName();
        String password = user.getPassword();
        String bcryptPasswd = bCryptPasswordEncoder.encode(password);
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

    @Override
    public Map getInfo(){
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        Map<String,Object> result = new HashMap<>();
        result.put("roles", roles);
        result.put("permissions", permissions);
        return result;
    }

    @Override
    public JSONObject getRouters() {
        JSONObject result = new JSONObject();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        //获取菜单表中menu_type为M、C的菜单记录。取得的集合是已经进行父子表递归过的
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        //构建前端所需要的路由表
        List<RouterVo> routerVos = menuService.buildMenus(menus);
        result.put("routers", routerVos);
        return result;
    }
}
