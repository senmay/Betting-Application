package com.dominik.typer.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableConfigurationProperties
@EnableAsync
@EnableAspectJAutoProxy
public class AppConfig {
}
