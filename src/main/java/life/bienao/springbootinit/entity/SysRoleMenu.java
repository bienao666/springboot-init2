package life.bienao.springbootinit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色和菜单关联 sys_role_menu
 *
 * @author ruoyi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleMenu  extends BaseEntity
{
    /** 角色ID */
    private Long roleId;

    /** 菜单ID */
    private Long menuId;
}
