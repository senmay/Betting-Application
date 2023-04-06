package com.dominik.typer.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@PropertySource(value = "classpath:database.properties", ignoreResourceNotFound = true)
@EnableConfigurationProperties
@EnableAsync
@EnableAspectJAutoProxy
public class AppConfig {
}
