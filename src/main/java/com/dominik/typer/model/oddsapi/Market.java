package com.dominik.typer.model.oddsapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Value
public class Market {

    @JsonProperty("key")
    String key;

    @JsonProperty("last_update")
    LocalDateTime lastUpdate;

    @JsonProperty("outcomes")
    List<Outcome> outcomes;

    // Getters and setters
}
