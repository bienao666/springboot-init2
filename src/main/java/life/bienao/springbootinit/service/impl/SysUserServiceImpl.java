package life.bienao.springbootinit.service.impl;

import life.bienao.springbootinit.constant.Redis;
import life.bienao.springbootinit.entity.*;
import life.bienao.springbootinit.enums.UserStatusEnum;
import life.bienao.springbootinit.mapper.SysUserMapper;
import life.bienao.springbootinit.mapper.SysUserRoleMapper;
import life.bienao.springbootinit.service.SysUserService;
import life.bienao.springbootinit.util.DateUtils;
import life.bienao.springbootinit.util.JwtUtil;
import life.bienao.springbootinit.util.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.lang.System;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SysUserServiceImpl
 * @Author bienao
 * @Date 4/2/2022 7:23 PM
 * @Description 用户业务层
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    protected BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 更新系统用户
     *
     * @param sysUser   系统用户更新的信息
     * @param updateOwn 是否是更新自己
     * @return
     */
    @Override
    public int updateSysUser(SysUser sysUser, Boolean updateOwn) {
        //设置当前更新用户的id、更新时间
        if (ObjectUtils.isEmpty(sysUser.getUserId())) {
            sysUser.setUserId(SecurityUtils.getUserId());
        }
        sysUser.setUpdateTime(new Date());
        //1、更新数据库用户信息
        int result = sysUserMapper.updateSysUser(sysUser);
        //2、更新redis用户信息
        if (result > 0) {
            //若是更新用户本身则更新redis、非用户本身不做更新（指的是管理员对某个用户更新信息）
            if (updateOwn) {
                //走数据库，查询一遍最新的用户信息、权限不做查询依旧使用原来的（因为管理员再进行对账号授权时，不会修改对应的缓存）
                SysUser user = sysUserMapper.loadUserByUsername(SecurityUtils.getUser().getUserName());
                LoginUser newLoginUser = new LoginUser(user, SecurityUtils.getLoginUser().getPermissions());
                newLoginUser.setToken(SecurityUtils.getLoginUser().getToken());//token(uuid)依旧是当前用户自身的
                //用户对象中保存登录时间以及过期时间
                newLoginUser.setLoginTime(System.currentTimeMillis());
                newLoginUser.setExpireTime(newLoginUser.getLoginTime() + JwtUtil.JWT_EXPIRETIME * JwtUtil.MILLIS_MINUTE);
                //存储到redis中，key为：loginUser中的token(uuid)
                String token = newLoginUser.getToken();
                Redis.loginUser.put("login:" + token, newLoginUser);
            }
        }
        return result;
    }

    @Override
    public List<SysUser> selectAllocatedList(SysUser user) {
        return sysUserMapper.selectAllocatedList(user);
    }

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    public List<SysUser> selectUnallocatedList(SysUser user) {
        return sysUserMapper.selectUnallocatedList(user);
    }

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
    public int updateSysUser(SysUser sysUser) {
        sysUser.setUpdateTime(DateUtils.getNowDate());
        return userMapper.updateSysUser(sysUser);
    }

    /**
     * 查询用户接口
     */
    @Override
    public List<MUserVo> selectSysUserList(MUserVo userVo) {
        List<MUserVo> list = userMapper.selectSysUserList(userVo);
        return list;
    }

    @Override
    public SysUser selectSysUserByUserId(Long userId) {
        return userMapper.selectSysUserByUserId(userId);
    }

    @Override
    public int updateSysUser(MUserVo userVo) {
        //仅仅只更新用户的真实姓名、性别
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userVo.getUserId());
        sysUser.setSex(userVo.getSex());
        sysUser.setRealName(userVo.getRealName());
        return updateSysUser(sysUser, false);
    }

    @Override
    public boolean cancelledSysUser(Long userId) {
        if (!ObjectUtils.isEmpty(userId)) {
            //1、注销账号
            if (userMapper.updateStatusByUserId(userId, UserStatusEnum.DISABLED.value()) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean activeSysUser(Long userId) {
        return userMapper.updateStatusByUserId(userId, UserStatusEnum.ACTIVE.value()) > 0;
    }

    /**
     * 检查当前用户是否为管理员
     *
     * @return yes return true or return false
     */
    public boolean checkUserIsManage() {
        for (SysRole role : SecurityUtils.getUser().getRoles()) {
            if (role.getRoleId() != null && role.getRoleId().equals(1L)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 重置密码为yml中的zf.resetPassword内容
     *
     * @param targetUserIds
     * @return
     */
    @Override
    public boolean resetPwd(Long[] targetUserIds) {
        //重置指定用户的账号为
        String password = bCryptPasswordEncoder.encode("123456");
        return userMapper.resetPwdByUserId(targetUserIds, password) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Long targetUserId) {
        //1、删除用户账号记录、用户角色记录
        if (sysUserMapper.deleteSysUserByUserId(targetUserId) > 0 &&
                sysUserRoleMapper.deleteUserRoleByUserId(targetUserId) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 更新用户
     *
     * @param profileVo 主页vo
     * @return
     */
    @Override
    public int updateProfile(ProfileVo profileVo) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(profileVo, sysUser);
        return updateSysUser(sysUser, true);
    }

    /**
     * 更新密码
     *
     * @param passwordVo 修改密码vo
     */
    @Override
    public void updatePasswd(PasswordVo passwordVo) {
        //1、校验密码
        String oldPassword = passwordVo.getOldPassword().trim();
        String newPassword = passwordVo.getPassword().trim();
        if (ObjectUtils.isEmpty(oldPassword) || ObjectUtils.isEmpty(newPassword)) {
            throw new RuntimeException("您的密码为空，重新输入！");
        }
        String userPassword = SecurityUtils.getUser().getPassword();
        //2、校验不通过
        if (!bCryptPasswordEncoder.matches(oldPassword, userPassword)) {
            throw new RuntimeException("您的旧密码有误，请重新输入！");
        }
        //3、校验通过，重新设置密码
        passwordVo.setPassword(bCryptPasswordEncoder.encode(newPassword));
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(passwordVo, sysUser);
        if (updateSysUser(sysUser, true) == 0) {
            throw new RuntimeException("修改失败！");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void registerUser(SysUser user) {
        //1、查询用户名是否存在
        SysUser sysUser = sysUserMapper.loadUserByUsername(user.getUserName());
        if (sysUser != null) {
            throw new RuntimeException("用户名已存在，请重新输入！");
        }
        //2、注册用户
        String username = user.getUserName();
        String password = user.getPassword();
        String bcryptPasswd = bCryptPasswordEncoder.encode(password);
        SysUser registerUser = new SysUser(username, username, bcryptPasswd);
        if (sysUserMapper.insertUser(registerUser) > 0) {
            //3、获取到刚刚自增的id
            Long userId = registerUser.getUserId();
            //4、分配用户角色（默认注册时是普通成员）
            List<SysUserRole> userRoles = new ArrayList<>(1);
            userRoles.add(new SysUserRole(userId, 2L));
            sysUserRoleMapper.batchUserRole(userRoles);
        } else {
            throw new RuntimeException("注册失败！");
        }
    }

}
