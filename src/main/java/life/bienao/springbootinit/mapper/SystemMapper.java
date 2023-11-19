package life.bienao.springbootinit.mapper;

import org.apache.ibatis.annotations.Mapper;
import life.bienao.springbootinit.entity.System;

import java.util.List;

/**
 * @description system
 */
@Mapper
public interface SystemMapper {

    /**
     * 新增
     **/
    int insert(System system);

    /**
     * 刪除
     **/
    int delete(int id);

    /**
     * 更新
     **/
    int update(System system);

    /**
     * 查询 根据主键 id 查询
     **/
    System load(int id);

    /**
     * 查询 所有
     **/
    List<System> loadAll();

}
