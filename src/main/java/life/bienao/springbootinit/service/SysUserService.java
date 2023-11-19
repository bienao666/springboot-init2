package life.bienao.springbootinit.service;


import life.bienao.springbootinit.entity.MUserVo;
import life.bienao.springbootinit.entity.PasswordVo;
import life.bienao.springbootinit.entity.ProfileVo;
import life.bienao.springbootinit.entity.SysUser;

import java.util.List;

/**
 * @ClassName ISysUserService
 * @Author bienao
 * @Date 4/2/2022 7:22 PM
 * @Description 系统用户业务层接口
 */
public interface SysUserService {

    /**
     * 更新系统用户
     * @param sysUser 系统用户对象
     * @return
     */
    int updateSysUser(SysUser sysUser, Boolean updateOwn);

    /**
     * 根据条件分页查询已分配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectAllocatedList(SysUser user);

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUnallocatedList(SysUser user);

    /**
     * 根据用户id来查询用户所有角色
     * @param userId 用户id
     * @return
     */
    String selectUserRoleGroup(SysUser user);

    /**
     * 更新用户信息
     * @param sysUser 系统用户
     * @return
     */
    int updateSysUser(SysUser sysUser);

    /**
     * 查询用户接口
     * @param userVo
     * @return
     */
    List<MUserVo> selectSysUserList(MUserVo userVo);

    /**
     * 获取用户信息表
     * @param userId
     * @return
     */
    SysUser selectSysUserByUserId(Long userId);

    /**
     * 更新用户信息
     * @param userVo
     * @return
     */
    int updateSysUser(MUserVo userVo);

    /**
     * 注销账号
     * @param userId
     * @return
     */
    boolean cancelledSysUser(Long userId);

    /**
     * 重置指定账号密码
     * @param targetUserId
     * @return
     */
    boolean resetPwd(Long[] targetUserId);

    /**
     * 删除指定用户账号
     * @param targetUserId
     * @return
     */
    boolean delete(Long targetUserId);

    /**
     * 激活用户账号
     * @param userId
     * @return
     */
    boolean activeSysUser(Long userId);

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

    void registerUser(SysUser user);

}
