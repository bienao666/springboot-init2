package life.bienao.springbootinit.mapper;


import life.bienao.springbootinit.entity.MUserVo;
import life.bienao.springbootinit.entity.ShowUserVo;
import life.bienao.springbootinit.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    SysUser loadUserByUsername(String username);

    /**
     * 根据用户id查询用户
     * @param userId 用户id
     * @return
     */
    SysUser selectUserById(Long userId);

    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    List<String> selectRolesByUserId(Long userId);


    /**
     * 创建用户
     * @param sysUser 用户
     * @return
     */
    Integer insertUser(SysUser sysUser);


    /**
     * 获取刚刚创建用户自增的id
     * @return
     */
    Long getLastInsertId();

    /**
     * 修改用户信息
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    public int updateSysUser(SysUser sysUser);

    /**
     * 删除User
     *
     * @param userId User主键
     * @return 结果
     */
    public int deleteSysUserByUserId(Long userId);

    /**
     * 根据条件分页查询已配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectAllocatedList(SysUser user);

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUnallocatedList(SysUser user);

    /**
     * 查询User列表
     *
     * @param userVo User
     * @return User集合
     */
    public List<MUserVo> selectSysUserList(MUserVo userVo);

    /**
     * 查询用于展示的用户数据列表
     */
    List<ShowUserVo> selectShowUserVoList();

    /**
     * 根据比赛id来查询到所有参与用户基本信息
     */
    List<ShowUserVo> selectRaceUsersByRaceId(Long raceId);

    /**
     * 查询所有用户的id以及真实姓名
     * @return
     */
    List<MUserVo> selectSysUserIdAndRealName();

    /**
     * 查询指定多个id的记录：包含基本信息有id、姓名、年级、年级id、专业、专业id
     * @return
     */
    List<MUserVo> selectSysUserByUserIds(Long[] ids);

    /**
     * 查询详细用户
     * @param userId
     * @return
     */
    SysUser selectSysUserByUserId(Long userId);

    /**
     * 更新用户状态
     * @param userId
     * @return
     */
    int updateStatusByUserId(@Param("userId")Long userId, @Param("status") String status);

    /**
     * 重置用户密码
     * @param targetUserIds
     * @return
     */
    int resetPwdByUserId(@Param("targetUserIds") Long[] targetUserIds,@Param("password") String password);

}
