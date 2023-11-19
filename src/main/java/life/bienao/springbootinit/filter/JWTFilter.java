package life.bienao.springbootinit.filter;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import life.bienao.springbootinit.constant.Constants;
import life.bienao.springbootinit.constant.Redis;
import life.bienao.springbootinit.entity.LoginUser;
import life.bienao.springbootinit.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        //获取token
        String token = httpServletRequest.getHeader(Constants.HEADER_KEY);
        if (StrUtil.isEmpty(token)) {
            //为空放行
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        //解析token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token.replace(Constants.BEARER, ""));
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //根据解析到的id查询redis
        LoginUser loginUser = Redis.loginUser.get("login:" + userId);
        if (Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登陆");
        }
        //将查询到的用户信息存入SecurityContextHolder
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
