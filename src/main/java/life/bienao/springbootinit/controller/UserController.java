package life.bienao.springbootinit.controller;

import life.bienao.springbootinit.entity.LoginBody;
import life.bienao.springbootinit.entity.MUserVo;
import life.bienao.springbootinit.entity.page.CommonPage;
import life.bienao.springbootinit.entity.page.PageUtils;
import life.bienao.springbootinit.service.AuthService;
import life.bienao.springbootinit.service.ManageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName ManagerUserController
 * @Author bienao
 * @Date 4/5/2022 5:53 PM
 * @Description 管理用户控制器
 */
@RestController
@RequestMapping("/member")
public class UserController {

    @Autowired
    private ManageUserService manageUserService;

    @Autowired
    private AuthService authService;

    /**
     * 查询用户成员列表（定制）
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPerm('member:list')")
    public CommonPage list(MUserVo userVo) {
        PageUtils.startPage();
        List<MUserVo> list = manageUserService.selectSysUserList(userVo);
        return CommonPage.restPage(list);
    }

    /**
     * 获取User详细信息
     */
    @GetMapping(value = "/{userId}")
    @PreAuthorize("@ss.hasPerm('member:query')")
    public MUserVo getInfo(@PathVariable("userId") Long userId)
    {
        return manageUserService.selectSysUserByUserId(userId);
    }

    /**
     * 新增用户
     */
    @PostMapping
    @PreAuthorize("@ss.hasPerm('member:add')")
    public void addUser(@RequestBody LoginBody loginBody){
        authService.registerUser(loginBody);
    }


    /**
     * 修改用户信息
     */
    @PutMapping
    @PreAuthorize("@ss.hasPerm('member:edit')")
    public void edit(@RequestBody MUserVo userVo) {
        manageUserService.updateSysUser(userVo);
    }

    /**
     *  注销账号
     */
    @DeleteMapping("/cancelled/{userId}")
    @PreAuthorize("@ss.hasPerm('member:cancelled')")
    public void cancelled(@PathVariable("userId") Long userId) {
        manageUserService.cancelledSysUser(userId);
    }

    /**
     *  激活账号
     */
    @PutMapping("/active/{userId}")
    @PreAuthorize("@ss.hasPerm('member:active')")
    public void active(@PathVariable("userId") Long userId) {
        manageUserService.activeSysUser(userId);
    }

    /**
     *  重置密码
     */
    @PutMapping("/resetpwd/{userIds}")
    @PreAuthorize("@ss.hasPerm('member:resetpwd')")
    public void resetPwd(@PathVariable(name = "userIds") Long[] userIds){
        manageUserService.resetPwd(userIds);
    }

    /**
     *  删除账号
     */
    @DeleteMapping("/{userId}")
    @PreAuthorize("@ss.hasPerm('member:remove')")
    public void delete(@PathVariable("userId") Long targetUserId){
        manageUserService.delete(targetUserId);
    }


}
