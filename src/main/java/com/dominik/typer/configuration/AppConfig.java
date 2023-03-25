package com.dominik.typer.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@PropertySource(value = "classpath:database.properties", ignoreResourceNotFound = true)
@PropertySource(value = "classpath:hi3.properties", ignoreResourceNotFound = true)
@PropertySource(value = "classpath:myapp.properties", ignoreResourceNotFound = true)
@PropertySource(value = "classpath:userpagesize.properties", ignoreResourceNotFound = true)
@EnableConfigurationProperties
@EnableAsync
//@ConfigurationPropertiesScan("com.dominik.typer.configuration")
//@ConfigurationPropertiesScan(basePackageClasses = MyAppProperties.class)
public class AppConfig {
}
