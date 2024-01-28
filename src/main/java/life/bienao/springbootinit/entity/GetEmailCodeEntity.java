package life.bienao.springbootinit.entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class GetEmailCodeEntity {
    @NotNull(message = "用户邮箱不能为空")
    @Email(message = "邮箱格式错位")
    private String email;
//    @NotNull(message = "用户账号不能为空")
    private String username;
//    @NotNull(message = "用户密码不能为空")
//    @Size(min=6, max=15,message="密码长度必须在 6 ~ 15 字符之间！")
//    @Pattern(regexp="^[a-zA-Z0-9|_]+$",message="密码必须由字母、数字、下划线组成！")
    private String password;
//    @NotNull(message = "用户昵称不能为空")
    private String nickname;
}
