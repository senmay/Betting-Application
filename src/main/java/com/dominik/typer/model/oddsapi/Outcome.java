package com.dominik.typer.model.oddsapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
@Value
public class Outcome {

    @JsonProperty("name")
    String name;

    @JsonProperty("price")
    double price;


}
