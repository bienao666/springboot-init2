package life.bienao.springbootinit.service.impl;

import com.github.pagehelper.PageHelper;
import life.bienao.springbootinit.constant.Systems;
import life.bienao.springbootinit.entity.System;
import life.bienao.springbootinit.entity.page.TableDataInfo;
import life.bienao.springbootinit.mapper.SystemMapper;
import life.bienao.springbootinit.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description system
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SystemMapper systemMapper;


    @Override
    public int insert(System system) {
        return systemMapper.insert(system);
    }


    @Override
    public int delete(int id) {
        return systemMapper.delete(id);
    }


    @Override
    public int update(System system) {
        int update = systemMapper.update(system);
        if (update > 0){
            Systems.datas.put(system.getCode(), system.getValue());
        }
        return update;
    }


    @Override
    public System load(int id) {
        return systemMapper.load(id);
    }


    @Override
    public List<System> pageList() {
        return systemMapper.loadAll();
    }

}
