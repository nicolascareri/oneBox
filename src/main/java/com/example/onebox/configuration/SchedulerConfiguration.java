package com.example.onebox.configuration;

import com.example.onebox.scheduler.DeleteCartScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class SchedulerConfiguration {

    @Bean("scheduledPaymentExecutor")
    public DeleteCartScheduler scheduledPaymentExecutor(){
        return new DeleteCartScheduler();
    }

}
