package life.bienao.springbootinit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName LoginBody
 * @Author bienao
 * @Date 3/28/2022 11:22 AM
 * @Description 登录请求体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginBody implements Serializable {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

}
