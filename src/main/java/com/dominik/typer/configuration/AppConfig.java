package com.dominik.typer.configuration;

import com.dominik.typer.aop.UpdateUserBalance;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan(basePackages = "com.dominik.typer", excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = UpdateUserBalance.class))
@PropertySource(value = "classpath:database.properties", ignoreResourceNotFound = true)
@PropertySource(value = "classpath:myapp.properties", ignoreResourceNotFound = true)
@EnableConfigurationProperties
@EnableAsync
@EnableAspectJAutoProxy
public class AppConfig {
}
