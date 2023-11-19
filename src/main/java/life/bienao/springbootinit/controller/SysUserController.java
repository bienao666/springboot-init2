package life.bienao.springbootinit.controller;

import life.bienao.springbootinit.entity.*;
import life.bienao.springbootinit.entity.page.CommonPage;
import life.bienao.springbootinit.entity.page.PageUtils;
import life.bienao.springbootinit.service.AuthService;
import life.bienao.springbootinit.service.SysUserService;
import life.bienao.springbootinit.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @Author bienao
 * @Date 4/5/2022 5:53 PM
 * @Description 管理用户控制器
 */
@RestController
@RequestMapping("/member")
public class SysUserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询用户成员列表
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPerm('member:list')")
    public CommonPage list(MUserVo userVo) {
        PageUtils.startPage();
        List<MUserVo> list = sysUserService.selectSysUserList(userVo);
        return CommonPage.restPage(list);
    }

    /**
     * 获取User详细信息
     */
    @GetMapping(value = "/{userId}")
    @PreAuthorize("@ss.hasPerm('member:query')")
    public SysUser getInfo(@PathVariable("userId") Long userId) {
        return sysUserService.selectSysUserByUserId(userId);
    }

    /**
     * 新增用户
     */
    @PostMapping(value = "/addUser")
    public void addUser(@RequestBody SysUser sysUser) {
        sysUserService.registerUser(sysUser);
    }


    /**
     * 修改用户信息
     */
    @PutMapping
    @PreAuthorize("@ss.hasPerm('member:update')")
    public void edit(@RequestBody MUserVo userVo) {
        sysUserService.updateSysUser(userVo);
    }

    /**
     * 注销账号
     */
    @DeleteMapping("/cancelled/{userId}")
    @PreAuthorize("@ss.hasPerm('member:cancelled')")
    public void cancelled(@PathVariable("userId") Long userId) {
        sysUserService.cancelledSysUser(userId);
    }

    /**
     * 激活账号
     */
    @PutMapping("/active/{userId}")
    @PreAuthorize("@ss.hasPerm('member:active')")
    public void active(@PathVariable("userId") Long userId) {
        sysUserService.activeSysUser(userId);
    }

    /**
     * 重置密码
     */
    @PutMapping("/resetpwd/{userIds}")
    @PreAuthorize("@ss.hasPerm('member:resetpwd')")
    public void resetPwd(@PathVariable(name = "userIds") Long[] userIds) {
        sysUserService.resetPwd(userIds);
    }

    /**
     * 删除账号
     */
    @DeleteMapping("/{userId}")
    @PreAuthorize("@ss.hasPerm('member:remove')")
    public void delete(@PathVariable("userId") Long targetUserId) {
        sysUserService.delete(targetUserId);
    }

    /**
     * 查询登陆用户的所有角色
     *
     * @return
     */
    @GetMapping("/profile")
    @PreAuthorize("@ss.hasPerm('member:profile')")
    public Map profile() {
        SysUser user = SecurityUtils.getUser();
        //使用,分割开来的
        String roleGroup = sysUserService.selectUserRoleGroup(user);
        Map result = new HashMap();
        result.put("user", user);
        result.put("roleGroup", roleGroup);
        return result;
    }

    /**
     * 更新用户信息
     *
     * @param profileVo
     */
    @PostMapping("/hasPerm")
    @PreAuthorize("@ss.hasRole('member:updateProfile')")
    public void updateProfile(@RequestBody ProfileVo profileVo) {
        int result = sysUserService.updateProfile(profileVo);
        if (result == 0) {
            throw new RuntimeException("更新失败！");
        }
    }

    /**
     * 修改密码
     *
     * @param passwordVo
     */
    @PostMapping("/updatePwd")
    @PreAuthorize("@ss.hasPerm('member:updatePwd')")
    public void updatePwd(@RequestBody PasswordVo passwordVo) {
        sysUserService.updatePasswd(passwordVo);
    }

}
