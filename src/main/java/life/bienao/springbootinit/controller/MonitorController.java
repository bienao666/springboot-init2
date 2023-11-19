package life.bienao.springbootinit.controller;

import life.bienao.springbootinit.entity.Server;
import life.bienao.springbootinit.service.MonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务监控
 *
 * @author bolo.bienao.life
 */
@Slf4j
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    /**
     * 获取所有监控
     *
     * @return
     */
    @GetMapping({"/getAllInfo"})
    @PreAuthorize("@ss.hasPerm('monitor:getAllInfo')")
    public Server getAllInfo() {
        return monitorService.getAllInfo();
    }
















}
