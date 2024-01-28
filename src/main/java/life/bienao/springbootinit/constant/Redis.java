package life.bienao.springbootinit.constant;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
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

    //创建缓存，默认30秒过期
    public static TimedCache<String, String> timedCache = CacheUtil.newTimedCache(30*1000);


}
