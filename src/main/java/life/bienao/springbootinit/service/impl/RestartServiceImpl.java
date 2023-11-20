package life.bienao.springbootinit.service.impl;

import life.bienao.springbootinit.service.RestartService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RestartServiceImpl implements RestartService {

    @Async
    @Override
    public void restart() {
        System.exit(0);
    }
}
