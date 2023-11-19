package life.bienao.springbootinit.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import life.bienao.springbootinit.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import javax.servlet.http.HttpServletRequest;
import java.net.ConnectException;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局处理自定义的业务异常
 * @Version 1.0
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error(e.getMessage(), e);
        return Result.error("数据库中已存在该记录");
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.error(e.getMessage(), e);
        return Result.error();
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(Exception e){
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ConnectException.class)
    public Result connectException(ConnectException e){
        log.error(e.getMessage(), e);
        return Result.error("系统调用异常");
    }

    @ResponseBody
    @ExceptionHandler(ResourceAccessException.class)
    public Result connectException(ResourceAccessException e){
        log.error(e.getMessage(), e);
        return Result.error("系统之间调用异常");
    }

    @ResponseBody
    @ExceptionHandler(value = {ExpiredJwtException.class})
    public Result expiredJwtException(ExpiredJwtException e) {
        log.error(e.getMessage(), e);
        return Result.error(401,"Token过期");
    }

    @ExceptionHandler(value = UnsupportedJwtException.class)
    @ResponseBody
    public Result unsupportedJwtException(UnsupportedJwtException e) {
        log.error(e.getMessage(), e);
        return Result.error(401,"Token签名失败");
    }

    @ExceptionHandler(value = SignatureException.class)
    @ResponseBody
    public Result signatureException(SignatureException e) {
        log.error(e.getMessage(), e);
        return Result.error(401,"Token格式错误");
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public Result illegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        return Result.error(401,"Token非法参数异常");
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    public Result accessDeniedException(AccessDeniedException e) {
        log.error(e.getMessage(), e);
        return Result.error(403,e.getMessage());
    }

    @ExceptionHandler(value = MalformedJwtException.class)
    @ResponseBody
    public Result malformedJwtException(MalformedJwtException e) {
        log.error(e.getMessage(), e);
        return Result.error(401,"Token没有被正确构造");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
                                                        HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        log.error("请求地址{},参数验证失败{}", requestURI, e.getObjectName(),e);
        return Result.error(message);
    }

    @ExceptionHandler(value = BadSqlGrammarException.class)
    @ResponseBody
    public Result handleBadSqlGrammarException(BadSqlGrammarException e,
                                               HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生数据库异常.", requestURI, e);
        return Result.error( "数据库异常！");
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseBody
    public Result handleDataIntegrityViolationException(DataIntegrityViolationException e,
                                                        HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生数据库异常.", requestURI, e);
        return Result.error( "数据库异常！");
    }
}
