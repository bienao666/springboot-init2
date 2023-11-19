package life.bienao.springbootinit.controller;

import life.bienao.springbootinit.entity.page.CommonPage;
import life.bienao.springbootinit.entity.System;
import life.bienao.springbootinit.service.SystemService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统参数
 */
@RestController
@RequestMapping(value = "/system")
public class SystemController {

    @Autowired
    private SystemService systemService;

    /**
     * 新增
     **/
    @PostMapping("/insert")
    @PreAuthorize("@ss.hasPerm('system:insert')")
    public int insert(@Validated @RequestBody System system){
        return systemService.insert(system);
    }

    /**
     * 刪除
     **/
    @DeleteMapping("/{id}")
    @PreAuthorize("@ss.hasPerm('system:delete')")
    public int delete(@PathVariable int id){
        return systemService.delete(id);
    }

    /**
     * 更新
     **/
    @PostMapping("/update")
    @PreAuthorize("@ss.hasPerm('system:update')")
    public int update(@Validated @RequestBody System system){
        return systemService.update(system);
    }

    /**
     * 查询 根据主键 id 查询
     **/
    @GetMapping("/load")
    @PreAuthorize("@ss.hasPerm('system:load')")
    public System load(@RequestParam int id){
        return systemService.load(id);
    }

    /**
     * 查询 分页查询
     **/
    @RequestMapping("/pageList")
    @PreAuthorize("@ss.hasPerm('system:pageList')")
    public CommonPage<System> pageList(@RequestParam(required = false, defaultValue = "1") int offset,
                                       @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return systemService.pageList(offset, pagesize);
    }

}
