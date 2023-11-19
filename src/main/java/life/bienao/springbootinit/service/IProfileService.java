package life.bienao.springbootinit.service;

import life.bienao.springbootinit.entity.PasswordVo;
import life.bienao.springbootinit.entity.ProfileVo;

/**
 * @ClassName ISysProfile
 * @Author bienao
 * @Date 4/2/2022 7:49 PM
 * @Description 个人主页业务层
 */
public interface IProfileService {


    /**
     * 更新主页信息
     * @param profileVo 主页vo
     * @return
     */
    int updateProfile(ProfileVo profileVo);

    /**
     * 修改密码
     * @param passwordVo 修改密码vo
     * @return
     */
    void updatePasswd(PasswordVo passwordVo);
}
