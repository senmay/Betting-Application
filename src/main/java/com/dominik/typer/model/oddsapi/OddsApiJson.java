package com.dominik.typer.model.oddsapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Value
public class OddsApiJson {
    @JsonProperty("id")
    String id;

    @JsonProperty("sport_key")
    String sportKey;

    @JsonProperty("sport_title")
    String sportTitle;

    @JsonProperty("commence_time")
    LocalDateTime commenceTime;

    @JsonProperty("home_team")
    String homeTeam;

    @JsonProperty("away_team")
    String awayTeam;

    @JsonProperty("bookmakers")
    List<Bookmaker> bookmakers;
}
