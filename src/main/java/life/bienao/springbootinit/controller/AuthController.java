package life.bienao.springbootinit.controller;

import com.alibaba.fastjson.JSONObject;
import life.bienao.springbootinit.entity.LoginBody;
import life.bienao.springbootinit.entity.RouterVo;
import life.bienao.springbootinit.entity.SysMenu;
import life.bienao.springbootinit.entity.SysUser;
import life.bienao.springbootinit.service.AuthService;
import life.bienao.springbootinit.service.SysMenuService;
import life.bienao.springbootinit.service.impl.SysPermissionService;
import life.bienao.springbootinit.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 权限控制
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private SysMenuService menuService;

    /**
     * 登陆
     * @param user
     * @return
     */
    @PostMapping("/login")
    public JSONObject login(@RequestBody SysUser user){
        return authService.login(user);
    }

    /**
     * 登出
     */
    @GetMapping ("/logout")
    public void logout(){
        authService.logout();
    }

    /**
     * 获取用户的角色和权限
     * @return
     */
    @GetMapping("/getInfo")
    public Map getInfo(){
        return authService.getInfo();
    }

    /**
     * 获取用户的路由
     * @return
     */
    @GetMapping("/getRouters")
    public JSONObject getRouters(){
        return authService.getRouters();
    }
}
