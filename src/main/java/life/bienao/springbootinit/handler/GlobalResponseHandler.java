package life.bienao.springbootinit.handler;

import com.alibaba.fastjson.JSONObject;
import life.bienao.springbootinit.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 响应统一封装
 * <p>
 * 将响应数据，封装成统一的数据格式。
 * <p>
 * 通过本处理器，将接口方法返回的数据，统一封装到 Result 的 data 字段中，如果接口方法返回为 void，则 data 字段的值为 null。
 */
@Slf4j
@RestControllerAdvice(basePackages = "life.bienao.springbootinit")
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    /**
     * 此组件是否支持给定的控制器方法返回类型和选定的 {@code HttpMessageConverter} 类型。
     *
     * @return 如果应该调用 {@link #beforeBodyWrite} ，则为 {@code true}；否则为false。
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 返回类型不为Result，才需要封装
        return returnType.getParameterType() != Result.class;
    }


    /**
     * 统一封装返回响应数据
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {

        // 数据封装为Result：将接口方法返回的数据，封装到 Result.data 字段中。
        Result result = Result.ok(body);

        // 返回类型不是 String：直接返回
        if (returnType.getParameterType() != String.class) {
            return result;
        }

        // 返回类型是 String：不能直接返回，需要进行额外处理
        // 1. 将 Content-Type 设为 application/json ；返回类型是String时，默认 Content-Type = text/plain
        HttpHeaders headers = response.getHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 2. 将 Result 转为 Json字符串 再返回
        // （否则会报错 java.lang.ClassCastException: com.example.core.model.Result cannot be cast to java.lang.String）
        return JSONObject.toJSONString(result);
    }

}
