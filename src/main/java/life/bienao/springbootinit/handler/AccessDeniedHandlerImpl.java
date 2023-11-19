package life.bienao.springbootinit.handler;

import com.alibaba.fastjson.JSONObject;
import life.bienao.springbootinit.entity.Result;
import life.bienao.springbootinit.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        //自定义认证失败处理器
        Result error = Result.error(HttpStatus.FORBIDDEN.value(), "您的权限不足请联系管理员!");
        WebUtils.renderString(httpServletResponse, JSONObject.toJSONString(error));
    }
}
