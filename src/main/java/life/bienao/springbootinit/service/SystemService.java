package life.bienao.springbootinit.service;


import life.bienao.springbootinit.entity.page.CommonPage;
import life.bienao.springbootinit.entity.System;

/**
 * @description system
 */
public interface SystemService {

    /**
     * 新增
     */
    public int insert(System system);

    /**
     * 删除
     */
    public int delete(int id);

    /**
     * 更新
     */
    public int update(System system);

    /**
     * 根据主键 id 查询
     */
    public System load(int id);

    /**
     * 分页查询
     */
    public CommonPage<System> pageList(int offset, int pagesize);

}
