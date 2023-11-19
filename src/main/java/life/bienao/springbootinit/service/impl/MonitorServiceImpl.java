package life.bienao.springbootinit.service.impl;

import life.bienao.springbootinit.entity.*;
import life.bienao.springbootinit.service.MonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MonitorServiceImpl implements MonitorService {
    @Override
    public Server getAllInfo() {
        Server server = new Server();
        try {
            server.copyTo();
        } catch (Exception e) {
            log.info("服务器监控异常：{}", e.getMessage());
        }
        return server;
    }
}
