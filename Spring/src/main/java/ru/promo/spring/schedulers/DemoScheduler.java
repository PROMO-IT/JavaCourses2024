package ru.promo.spring.schedulers;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DemoScheduler {

    @Autowired
    private DemoService demoService;

    @Scheduled(fixedDelayString = "PT2S")
    @SchedulerLock(name = "DemoScheduler.demo", lockAtMostFor = "PT1M")
    public void demo() {
        demoService.test();
    }
}
