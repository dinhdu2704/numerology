package com.microservices.numerology.infra.rest.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class RestConfig {

    @Bean
    public MessageSource titleSource() {
        var messageBundleResource = new ResourceBundleMessageSource();
        messageBundleResource.setBasename("titles");
        messageBundleResource.setAlwaysUseMessageFormat(true);
        return messageBundleResource;
    }

    @Bean
    public MessageSource messageSource() {
        var messageBundleResource = new ResourceBundleMessageSource();
        messageBundleResource.setBasename("messages");
        messageBundleResource.setAlwaysUseMessageFormat(true);
        return messageBundleResource;
    }

}
