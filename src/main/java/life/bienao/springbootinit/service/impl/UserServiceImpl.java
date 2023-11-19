package life.bienao.springbootinit.service.impl;

import life.bienao.springbootinit.entity.SysUser;
import life.bienao.springbootinit.mapper.SysUserMapper;
import life.bienao.springbootinit.service.IUserService;
import life.bienao.springbootinit.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName UserServiceImpl
 * @Author bienao
 * @Date 3/28/2022 10:39 PM
 * @Description 用户接口
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public String selectUserRoleGroup(SysUser user) {
        List<String> roles = userMapper.selectRolesByUserId(user.getUserId());
        return roles.stream().collect(Collectors.joining(","));
    }

    /**
     * 修改用户信息
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    @Override
    public int updateSysUser(SysUser sysUser){
        sysUser.setUpdateTime(DateUtils.getNowDate());
        return userMapper.updateSysUser(sysUser);
    }

}
