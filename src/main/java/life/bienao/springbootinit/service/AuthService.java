package life.bienao.springbootinit.service;

import com.alibaba.fastjson.JSONObject;
import life.bienao.springbootinit.entity.*;

import java.util.Map;

public interface AuthService {

    JSONObject login(SysUser user);

    void logout();

    /**
     * 注册用户
     * @param loginBody 用户主体
     * @return
     */
    void registerUser(SysUser user);

    public Map getInfo();

    JSONObject getRouters();

    void emailCode(GetEmailCodeEntity entity);

    void register(RegisterEntity entity);

    void resetPassword(ResetPasswordEntity entity);
}
