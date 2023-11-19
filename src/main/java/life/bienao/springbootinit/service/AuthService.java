package life.bienao.springbootinit.service;

import com.alibaba.fastjson.JSONObject;
import life.bienao.springbootinit.entity.LoginBody;
import life.bienao.springbootinit.entity.SysUser;

public interface AuthService {

    JSONObject login(SysUser user);

    void logout();

    /**
     * 注册用户
     * @param loginBody 用户主体
     * @return
     */
    void registerUser(LoginBody loginBody);
}
