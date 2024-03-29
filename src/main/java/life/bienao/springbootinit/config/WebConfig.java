package life.bienao.springbootinit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 跨域问题
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry){
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                //是否允许cookie
                .allowCredentials(true)
                //设置允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                //设置允许的header属性
                .allowedHeaders("*")
                //跨域允许时间
                .maxAge(3600);
    }

    /**
     * 所有/路径都代理到index.html下
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.addViewController("/view/**").setViewName("forward:/index.html");
    }


}
