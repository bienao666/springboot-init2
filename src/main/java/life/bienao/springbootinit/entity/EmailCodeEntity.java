package life.bienao.springbootinit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailCodeEntity {
    private String emailCode;
    private String username;
    private String email;
    private int times;
}
