package life.bienao.springbootinit.service.impl;

import life.bienao.springbootinit.entity.LoginUser;
import life.bienao.springbootinit.entity.SysUser;
import life.bienao.springbootinit.mapper.SysMenuMapper;
import life.bienao.springbootinit.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserMapper.loadUserByUsername(username);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或密码错误");
        }
        List<String> permissions = sysMenuMapper.selectPermsByUserId(user.getUserId());
        return new LoginUser(user, permissions);
    }

}
