package com.dominik.typer.model.mapper;

import com.dominik.typer.model.Team;
import com.dominik.typer.model.oddsapi.MatchApiJson;
import com.dominik.typer.service.teampersistence.TeamService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class ApiMapper {
    private final TeamService teamService;

    public MatchApiJson createMatchFromJsonNode(JsonNode node) {
        String id = node.get("id").asText();
        String homeTeam = node.get("home_team").asText();
        String awayTeam = node.get("away_team").asText();
        LocalDateTime commenceTime = LocalDateTime.parse(node.get("commence_time").asText(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        JsonNode bookmakers = node.get("bookmakers");
        Double oddsForHomeTeam = null;
        Double oddsForDraw = null;
        Double oddsForAwayTeam = null;
        JsonNode unibetBookmaker = findBookmakerByTitle(bookmakers, "Unibet");
        if (unibetBookmaker != null) {
            JsonNode unibetMarkets = unibetBookmaker.get("markets");
            JsonNode unibetH2HMarket = findMarketByKey(unibetMarkets, "h2h");
            if (unibetH2HMarket != null) {
                oddsForHomeTeam = getOddsForOutcome(unibetH2HMarket.get("outcomes"), homeTeam);
                oddsForDraw = getOddsForOutcome(unibetH2HMarket.get("outcomes"), "Draw");
                oddsForAwayTeam = getOddsForOutcome(unibetH2HMarket.get("outcomes"), awayTeam);
            }
        }
        Team homeTeamResult = teamService.getTeamByName(node.get("home_team").asText()).get();
        Team awayTeamResult = teamService.getTeamByName(node.get("away_team").asText()).get();

        return MatchApiJson.builder()
                .apiId(id)
                .homeTeamId(homeTeamResult.getId())
                .awayTeamId(awayTeamResult.getId())
                .commenceTime(commenceTime)
                .oddsForHomeTeam(oddsForHomeTeam)
                .oddsForDraw(oddsForDraw)
                .oddsForAwayTeam(oddsForAwayTeam)
                .build();
    }
    private Double getOddsForOutcome(JsonNode outcomes, String outcomeName) {
        for (JsonNode outcome : outcomes) {
            if (outcome.get("name").asText().equalsIgnoreCase(outcomeName)) {
                return outcome.get("price").asDouble();
            }
        }
        return null;
    }

    private JsonNode findMarketByKey(JsonNode markets, String key) {
        for (JsonNode market : markets) {
            if (market.get("key").asText().equalsIgnoreCase(key)) {
                return market;
            }
        }
        return null;
    }

    private JsonNode findBookmakerByTitle(JsonNode bookmakers, String title) {
        for (JsonNode bookmaker : bookmakers) {
            if (bookmaker.get("title").asText().equalsIgnoreCase(title)) {
                return bookmaker;
            }
        }
        return null;
    }
}
