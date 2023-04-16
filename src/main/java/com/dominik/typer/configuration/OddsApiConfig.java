package com.dominik.typer.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "oddsapi")
@Configuration
@Data
public class OddsApiConfig {
    @Value("${oddsapi.url}")
    private String url;
    @Value("${oddsapi.apikey}")
    private String apiKey;

}

