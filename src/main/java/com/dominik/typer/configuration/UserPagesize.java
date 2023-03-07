package com.dominik.typer.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "userpagesize")
@Data
public class UserPagesize {
    private int pageSize;

}
