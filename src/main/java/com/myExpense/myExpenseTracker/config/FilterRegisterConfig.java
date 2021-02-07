package com.myExpense.myExpenseTracker.config;

import com.myExpense.myExpenseTracker.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegisterConfig {

    @Bean
    public FilterRegistrationBean<AuthFilter> filterRegistrationBean() {

        FilterRegistrationBean<AuthFilter> result = new FilterRegistrationBean<>();

        result.setFilter(new AuthFilter());

        result.addUrlPatterns("/category/*");

        return result;

    }
}
