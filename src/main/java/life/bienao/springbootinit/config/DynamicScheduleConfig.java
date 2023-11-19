package life.bienao.springbootinit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * 动态定时任务配置类
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果
@EnableScheduling   //2.开启定时任务
public class DynamicScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        /*taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> taskService.testZone(),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = Systems.datas.get("test_cron").toString();
                    //2.2 合法性校验.
                    if (StrUtil.isNotEmpty(cron)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );*/
    }
}
