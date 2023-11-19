package life.bienao.springbootinit.controller;

import com.alibaba.fastjson.JSONObject;
import life.bienao.springbootinit.entity.LoginBody;
import life.bienao.springbootinit.entity.RouterVo;
import life.bienao.springbootinit.entity.SysMenu;
import life.bienao.springbootinit.entity.SysUser;
import life.bienao.springbootinit.service.AuthService;
import life.bienao.springbootinit.service.ISysMenuService;
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
    private SysPermissionService permissionService;

    @Autowired
    private ISysMenuService menuService;

    @PostMapping("/login")
    public JSONObject login(@RequestBody SysUser user){
        return authService.login(user);
    }

    @GetMapping ("/logout")
    public void logout(){
        authService.logout();
    }

    @PostMapping("/register")
    public void register(@RequestBody LoginBody loginBody){
        authService.registerUser(loginBody);
    }


    @GetMapping("/getInfo")
    public Map getInfo(){
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        Map<String,Object> result = new HashMap<>();
        result.put("user", user);
        result.put("roles", roles);
        result.put("permissions", permissions);
        return result;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public List<RouterVo> getRouters(){
        Long userId = SecurityUtils.getUserId();
        //获取菜单表中menu_type为M、C的菜单记录。取得的集合是已经进行父子表递归过的
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        //构建前端所需要的路由表
        return menuService.buildMenus(menus);
    }
}
