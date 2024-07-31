package ru.promo.spring.lesson11;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    @Primary
    public String description1() {
        return "tsk";
    }

    @Bean
    public String descr() {
        return "task desc";
    }

    @Bean("configCustomEmp")
    public Employee employee() {
        var employee = new Employee();
        employee.setEmail("test@gmail.com");
        employee.setName("Jack");
        return employee;
    }
}
