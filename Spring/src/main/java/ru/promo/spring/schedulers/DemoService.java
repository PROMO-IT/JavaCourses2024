package ru.promo.spring.schedulers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class DemoService {

    public void test() {
        log.info("Now is {}", LocalDateTime.now());
    }
}
