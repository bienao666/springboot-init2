package life.bienao.springbootinit.constant;

import life.bienao.springbootinit.entity.LoginUser;

import java.util.HashMap;
import java.util.Map;

public class Redis {

    /**
     * 用户信息缓存
     */
    public static Map<String, LoginUser> loginUser = new HashMap<>();

    /**
     * 其他数据
     */
    public static Map<String, Object> data = new HashMap<>();


}
