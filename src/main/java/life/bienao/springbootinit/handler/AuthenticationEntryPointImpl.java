package life.bienao.springbootinit.handler;

import com.alibaba.fastjson.JSONObject;
import life.bienao.springbootinit.entity.Result;
import life.bienao.springbootinit.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Result error = Result.error(HttpStatus.UNAUTHORIZED.value(), "认证失败请重新登录");
        WebUtils.renderString(httpServletResponse, JSONObject.toJSONString(error));
    }
}
