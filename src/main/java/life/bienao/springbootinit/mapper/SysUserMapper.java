package life.bienao.springbootinit.mapper;


import life.bienao.springbootinit.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

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

}
