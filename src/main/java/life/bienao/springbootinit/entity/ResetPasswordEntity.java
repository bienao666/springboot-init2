package life.bienao.springbootinit.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class ResetPasswordEntity {
    @NotEmpty(message = "用户密码不能为空")
    @Length(min = 6,max = 18,message="密码必须是6-18位")
    private String newPassword;
    @NotEmpty(message = "用户邮箱不能为空")
    @Email(message = "邮箱格式错位")
    private String email;
    @NotEmpty(message = "邮箱验证码不能为空")
    private String authCode;
}
