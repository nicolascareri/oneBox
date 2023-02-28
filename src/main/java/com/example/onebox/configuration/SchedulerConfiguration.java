package com.example.onebox.configuration;

import com.example.onebox.scheduler.CartScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class SchedulerConfiguration {

    @Bean("scheduledPaymentExecutor")
    public CartScheduler scheduledPaymentExecutor(){
        return new CartScheduler();
    }

}
