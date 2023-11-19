package life.bienao.springbootinit.service.impl;

import life.bienao.springbootinit.entity.MUserVo;
import life.bienao.springbootinit.entity.SysRole;
import life.bienao.springbootinit.entity.SysUser;
import life.bienao.springbootinit.enums.UserStatusEnum;
import life.bienao.springbootinit.mapper.MUserMapper;
import life.bienao.springbootinit.mapper.SysUserMapper;
import life.bienao.springbootinit.mapper.SysUserRoleMapper;
import life.bienao.springbootinit.service.ISysUserService;
import life.bienao.springbootinit.service.ManageUserService;
import life.bienao.springbootinit.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ZfManageUserServiceImpl
 * @Author bienao
 * @Date 4/5/2022 5:55 PM
 * @Description 管理用户业务层实现类
 */
@Service
public class ManageUserServiceImpl implements ManageUserService {

    @Resource
    private MUserMapper userMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISysUserService sysUserService;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 查询用户接口
     */
    @Override
    public List<MUserVo> selectSysUserList(MUserVo userVo) {
        List<MUserVo> list = userMapper.selectSysUserList(userVo);
        return list;
    }

    @Override
    public MUserVo selectSysUserByUserId(Long userId) {
        return userMapper.selectSysUserByUserId(userId);
    }

    @Override
    public int updateSysUser(MUserVo userVo) {
        //仅仅只更新用户的真实姓名、性别
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userVo.getUserId());
        sysUser.setSex(userVo.getSex());
        sysUser.setRealName(userVo.getRealName());
        return sysUserService.updateSysUser(sysUser, false);
    }

    @Override
    public boolean cancelledSysUser(Long userId) {
        if (!ObjectUtils.isEmpty(userId) ){
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
     * @param targetUserIds
     * @return
     */
    @Override
    public boolean resetPwd(Long[] targetUserIds) {
        //重置指定用户的账号为
        String password = SecurityUtils.bCryptPasswordEncoder.encode("123456");
        return userMapper.resetPwdByUserId(targetUserIds,password) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Long targetUserId) {
        //1、删除用户账号记录、用户角色记录
        if (sysUserMapper.deleteSysUserByUserId(targetUserId) > 0 &&
            sysUserRoleMapper.deleteUserRoleByUserId(targetUserId) > 0){
            return true;
        }
        return false;
    }




}
