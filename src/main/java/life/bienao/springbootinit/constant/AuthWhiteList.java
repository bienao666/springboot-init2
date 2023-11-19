package life.bienao.springbootinit.constant;

/**
 * 描述：白名单
 */
public class AuthWhiteList {

    /**
     * 需要放行的URL
     */
    public static final String[] AUTH_WHITELIST = {
            // -- register url
            "/member/addUser",
            "/auth/login",
            "/init/**",
            "/assets/**",
            "/img/**",
            "/tinymce/**",
            "/",
            // other public endpoints of your API may be appended to this array
    };
}
