package life.bienao.springbootinit.controller;

import life.bienao.springbootinit.constant.Constants;
import life.bienao.springbootinit.entity.LoginUser;
import life.bienao.springbootinit.entity.SysRole;
import life.bienao.springbootinit.entity.SysUser;
import life.bienao.springbootinit.entity.SysUserRole;
import life.bienao.springbootinit.entity.page.CommonPage;
import life.bienao.springbootinit.entity.page.PageUtils;
import life.bienao.springbootinit.service.ISysRoleService;
import life.bienao.springbootinit.service.ISysUserService;
import life.bienao.springbootinit.util.SecurityUtils;
import life.bienao.springbootinit.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SysRoleController
 * @Author bienao
 * @Date 4/10/2022 10:04 PM
 * @Description 系统角色控制器
 */
@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysUserService sysUserService;


    @GetMapping("/list")
    @PreAuthorize("@ss.hasPerm('role:list')")
    public List<SysRole> list(SysRole role) {
        return roleService.selectRoleList(role);
    }

    /**
     * 状态修改
     */
    @PutMapping("/changeStatus")
    @PreAuthorize("@ss.hasPerm('role:changeStatus')")
    public void changeStatus(@RequestBody SysRole role) {
        roleService.checkRoleAllowed(role);
        roleService.checkRoleDataScope(role.getRoleId());
        String username = SecurityUtils.getLoginUser().getUsername();
        role.setUpdateBy(username);
        if (roleService.updateRoleStatus(role) < 0) {
            throw new RuntimeException("修改失败");
        }
    }

    /**
     * 根据角色编号获取详细信息
     */
    @GetMapping(value = "/{roleId}")
    @PreAuthorize("@ss.hasPerm('role:query')")
    public SysRole getInfo(@PathVariable Long roleId) {
        roleService.checkRoleDataScope(roleId);
        return roleService.selectRoleById(roleId);
    }

    /**
     * 新增角色
     */
    @PostMapping
    @PreAuthorize("@ss.hasPerm('role:add')")
    public void add(@Validated @RequestBody SysRole role) {
        if (Constants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))){
            throw new RuntimeException("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (Constants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            throw new RuntimeException("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        String username = SecurityUtils.getLoginUser().getUsername();
        role.setCreateBy(username);
        roleService.insertRole(role);
    }

    /**
     * 修改保存角色
     */
    @PutMapping
    @PreAuthorize("@ss.hasPerm('role:edit')")
    public void edit(@RequestBody SysRole role) {
        roleService.checkRoleAllowed(role);//不允许对admin角色继续权限修改操作（有一个判断逻辑）
        roleService.checkRoleDataScope(role.getRoleId());//判断当前角色是否存在（就是根据当前的传入的sysrole来查询角色对象看是否有该角色）
        //检测角色名（rolename）中文名称是否唯一
        if (Constants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))){
            throw new RuntimeException("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }else if (Constants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            //检测rolekey是否唯一，也就是表示角色的英文键
            throw new RuntimeException("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        String username = SecurityUtils.getLoginUser().getUsername();
        role.setUpdateBy(username);//设置更新者
        //updateRole逻辑过程：①修改角色表。②删除角色_菜单表中关于该角色的所有记录。③查询最新该角色关联的角色记录【有使用异常回滚注解】
        if (roleService.updateRole(role) > 0)  {
            // 更新当前用户权限
            LoginUser loginUser = SecurityUtils.getLoginUser();
            if (StringUtils.isNotNull(loginUser.getUser()) && !loginUser.getUser().isAdmin()) {
                //更新当前用户状态
                sysUserService.updateSysUser(loginUser.getUser(), true);
            }
        }else {
            throw new RuntimeException("修改角色'" + role.getRoleName() + "'失败，请联系管理员");
        }
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/{roleIds}")
    @PreAuthorize("@ss.hasPerm('role:remove')")
    public void remove(@PathVariable Long[] roleIds){
        roleService.deleteRoleByIds(roleIds);
    }

    /**
     * 查询已分配用户角色列表
     */
    @GetMapping("/authUser/allocatedList")
    @PreAuthorize("@ss.hasPerm('role:authUser:allocatedList')")
    public CommonPage allocatedList(SysUser user){
        PageUtils.startPage();
        List<SysUser> list = sysUserService.selectAllocatedList(user);
        return CommonPage.restPage(list);
    }

    /**
     * 查询未分配用户角色列表
     */
    @GetMapping("/authUser/unallocatedList")
    @PreAuthorize("@ss.hasPerm('role:authUser:unallocatedList')")
    public CommonPage unallocatedList(SysUser user) {
        PageUtils.startPage();
        List<SysUser> list = sysUserService.selectUnallocatedList(user);
        return CommonPage.restPage(list);
    }

    /**
     * 取消授权用户
     */
    @PutMapping("/authUser/cancel")
    @PreAuthorize("@ss.hasPerm('role:authUser:cancel')")
    public void cancelAuthUser(@RequestBody SysUserRole userRole) {
        roleService.deleteAuthUser(userRole);
    }

    /**
     * 批量选择用户授权
     */
    @PutMapping("/authUser/selectAll")
    @PreAuthorize("@ss.hasPerm('role:authUser:selectAll')")
    public void selectAuthUserAll(Long roleId, Long[] userIds) {
        roleService.checkRoleDataScope(roleId);
        roleService.insertAuthUsers(roleId, userIds);
    }

    /**
     * 批量取消授权用户
     */
    @PutMapping("/authUser/cancelAll")
    @PreAuthorize("@ss.hasPerm('role:authUser:cancelAll')")
    public void cancelAuthUserAll(Long roleId, Long[] userIds) {
        roleService.deleteAuthUsers(roleId, userIds);
    }


}
