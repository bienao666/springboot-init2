package life.bienao.springbootinit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户和角色关联 sys_user_role
 *
 * @author ruoyi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRole extends BaseEntity
{
    /** 用户ID */
    private Long userId;

    /** 角色ID */
    private Long roleId;
}
