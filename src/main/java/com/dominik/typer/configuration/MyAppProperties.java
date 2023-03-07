package com.dominik.typer.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties(prefix = "myapp")
@Configuration
@Data
public class MyAppProperties {

    private String name;
    private Integer age;
    private String dogName;
    private Integer dogAge;
}
