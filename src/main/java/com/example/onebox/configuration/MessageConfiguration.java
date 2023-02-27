package com.example.onebox.configuration;

import com.example.onebox.utils.MessageUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageConfiguration {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages/messages");

        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public static MessageUtils messageI18NUtils() {
        return new MessageUtils();
    }

}
