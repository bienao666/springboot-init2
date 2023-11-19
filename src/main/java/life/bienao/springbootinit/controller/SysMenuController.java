package life.bienao.springbootinit.controller;

import life.bienao.springbootinit.constant.Constants;
import life.bienao.springbootinit.entity.SysMenu;
import life.bienao.springbootinit.entity.TreeSelect;
import life.bienao.springbootinit.service.SysMenuService;
import life.bienao.springbootinit.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 * @Author bienao
 * @Date 4/10/2022 10:40 PM
 * @Description 系统菜单控制器
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService menuService;

    /**
     * 获取菜单列表
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPerm('menu:list')")
    public List<SysMenu> list(SysMenu menu){
        //根据当前的用户权限来查询出所有的菜单
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        return menuService.selectMenuList(menu, userId);
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    @PreAuthorize("@ss.hasPerm('menu:treeselect')")
    public List<TreeSelect> treeselect(SysMenu menu){
        //根据当前的调用接口的用户id查看到所有的菜单项
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        List<SysMenu> menus = menuService.selectMenuList(menu, userId);
        return menuService.buildMenuTreeSelect(menus);
    }

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    @PreAuthorize("@ss.hasPerm('menu:roleMenuTree')")
    public Map<String,Object> roleMenuTreeselect(@PathVariable("roleId") Long roleId){
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        List<SysMenu> menus = menuService.selectMenuList(userId);//若是管理员则查询出所有的菜单信息（根据当前用户的权限）
        Map<String,Object> result = new HashMap<>(2);
        //根据角色id获取菜单列表
        result.put("checkedKeys", menuService.selectMenuListByRoleId(roleId));//对应角色具备的菜单信息
        //根据用户菜单来构建菜单树
        result.put("menus", menuService.buildMenuTreeSelect(menus));
        return result;
    }

    /**
     * 新增菜单
     */
    @PostMapping
    @PreAuthorize("@ss.hasPerm('menu:add')")
    public void add(@Validated @RequestBody SysMenu menu){
        //检测菜单的唯一性
        if (Constants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))){
            throw new RuntimeException("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        String username = SecurityUtils.getLoginUser().getUsername();
        menu.setCreateBy(username);
        menuService.insertMenu(menu);
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @GetMapping(value = "/{menuId}")
    @PreAuthorize("@ss.hasPerm('menu:query')")
    public SysMenu getInfo(@PathVariable Long menuId){
        return menuService.selectMenuById(menuId);
    }

    /**
     * 修改菜单
     */
    @PutMapping
    @PreAuthorize("@ss.hasPerm('menu:edit')")
    public void edit(@Validated @RequestBody SysMenu menu){
        if (Constants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))){
            throw new RuntimeException("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (menu.getMenuId().equals(menu.getParentId())){
            throw new RuntimeException("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        String username = SecurityUtils.getLoginUser().getUsername();
        menu.setUpdateBy(username);
        menuService.updateMenu(menu);
    }

    /**
     * 删除菜单
     */
    @DeleteMapping("/{menuId}")
    @PreAuthorize("@ss.hasPerm('menu:remove')")
    public void remove(@PathVariable("menuId") Long menuId){
        if (menuService.hasChildByMenuId(menuId)){
            throw new RuntimeException("存在子菜单,不允许删除");
        } if (menuService.checkMenuExistRole(menuId)){
            throw new RuntimeException("菜单已分配,不允许删除");
        }
        menuService.deleteMenuById(menuId);
    }

}
