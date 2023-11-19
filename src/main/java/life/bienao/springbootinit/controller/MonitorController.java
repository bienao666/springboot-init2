package life.bienao.springbootinit.controller;

import life.bienao.springbootinit.entity.Server;
import life.bienao.springbootinit.service.MonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 监控 Controller
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
     * 获取所有
     *
     * @return
     */
    @GetMapping({"/getAllInfo"})
    public Server getAllInfo() {
        return monitorService.getAllInfo();
    }
















}
