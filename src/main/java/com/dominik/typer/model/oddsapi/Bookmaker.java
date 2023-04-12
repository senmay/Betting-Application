package com.dominik.typer.model.oddsapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value

public class Bookmaker {
    @JsonProperty("key")
    String key;

    @JsonProperty("title")
    String title;

    @JsonProperty("markets")
    List<Market> markets;
}
