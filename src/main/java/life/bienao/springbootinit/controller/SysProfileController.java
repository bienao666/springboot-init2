package life.bienao.springbootinit.controller;

import life.bienao.springbootinit.entity.PasswordVo;
import life.bienao.springbootinit.entity.ProfileVo;
import life.bienao.springbootinit.entity.SysUser;
import life.bienao.springbootinit.service.IProfileService;
import life.bienao.springbootinit.service.IUserService;
import life.bienao.springbootinit.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SysProfileController
 * @Author bienao
 * @Date 3/28/2022 10:26 PM
 * @Description 系统用户控制器
 */
@RestController
@RequestMapping("/user")
public class SysProfileController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IProfileService profileService;

    @GetMapping("/profile")
    @PreAuthorize("@ss.hasRole('member')")
    public Map profile(){
        SysUser user = SecurityUtils.getUser();
        //使用,分割开来的
        String roleGroup = userService.selectUserRoleGroup(user);
        Map result = new HashMap();
        result.put("user", user);
        result.put("roleGroup", roleGroup);
        return result;
    }

    @PostMapping("/profile")
    @PreAuthorize("@ss.hasRole('member')")
    public void updateProfile(@RequestBody ProfileVo profileVo){
        int result = profileService.updateProfile(profileVo);
        if (result == 0) {
            throw new RuntimeException("更新失败！");
        }
    }

    @PostMapping("/updatePwd")
    @PreAuthorize("@ss.hasRole('member')")
    public void updatePwd(@RequestBody PasswordVo passwordVo){
        profileService.updatePasswd(passwordVo);
    }

}
