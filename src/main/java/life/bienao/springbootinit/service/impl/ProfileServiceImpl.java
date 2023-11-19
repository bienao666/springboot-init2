package life.bienao.springbootinit.service.impl;

import life.bienao.springbootinit.entity.PasswordVo;
import life.bienao.springbootinit.entity.ProfileVo;
import life.bienao.springbootinit.entity.SysUser;
import life.bienao.springbootinit.service.IProfileService;
import life.bienao.springbootinit.service.ISysUserService;
import life.bienao.springbootinit.util.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @ClassName ProfileServiceImpl
 * @Author bienao
 * @Date 4/2/2022 7:50 PM
 * @Description 用户业务实现层
 */
@Service
public class ProfileServiceImpl implements IProfileService {

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public int updateProfile(ProfileVo profileVo) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(profileVo, sysUser);
        return sysUserService.updateSysUser(sysUser, true);
    }

    @Override
    public void updatePasswd(PasswordVo passwordVo) {
        //1、校验密码
        String oldPassword = passwordVo.getOldPassword().trim();
        String newPassword = passwordVo.getPassword().trim();
        if (ObjectUtils.isEmpty(oldPassword) || ObjectUtils.isEmpty(newPassword)){
            throw new RuntimeException("您的密码为空，重新输入！");
        }
        String userPassword = SecurityUtils.getUser().getPassword().substring(8);
        //2、校验不通过
        if (!SecurityUtils.bCryptPasswordEncoder.matches(oldPassword, userPassword)) {
            throw new RuntimeException("您的旧密码有误，请重新输入！");
        }
        //3、校验通过，重新设置密码
        passwordVo.setPassword(SecurityUtils.bCryptPasswordEncoder.encode(newPassword));
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(passwordVo, sysUser);
        if (sysUserService.updateSysUser(sysUser, true) == 0) {
            throw new RuntimeException("修改失败！");
        }
    }
}
