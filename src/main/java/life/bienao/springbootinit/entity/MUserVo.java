package life.bienao.springbootinit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName MUserVo
 * @Author bienao
 * @Date 4/5/2022 5:43 PM
 * @Description 工作室管理-用户视图
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MUserVo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户角色id（允许多个，该字段主要用于查询）
     */
    private String roleIds;

    /**
     * 用户角色：连表
     */
    private String roleName;

    /**
     * 真实姓名
     */
    private String realName;

    /** 用户账号名 */
    private String userName;

    /**
     * 手机号码
     */
    private String phonenumber;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;


    /** 个人照片 */
    private String perImg;

    /** 个人描述 */
    private String description;

}
