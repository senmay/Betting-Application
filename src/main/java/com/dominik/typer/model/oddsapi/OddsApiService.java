package com.dominik.typer.model.oddsapi;

import com.dominik.typer.configuration.OddsApiConfig;
import com.dominik.typer.enumerations.League;
import com.dominik.typer.model.Match;
import com.dominik.typer.model.mapper.ApiMapper;
import com.dominik.typer.model.mapper.MatchMapper;
import com.dominik.typer.service.matchpersistence.MatchService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OddsApiService {
    private final OddsApiConfig oddsApiConfig;
    private final ApiMapper apiMapper;
    private final MatchService matchService;
    private final MatchMapper matchMapper;

    public List<Match> fetchUpcomingMatchesData(String league) {
        HttpRequest request = buildHttpRequest(league);
        try {
            HttpResponse<String> response = sendRequest(request);
            return handleResponse(response);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Match> processAndSaveMatches(List<JsonNode> nodes) {
        List<Match> matches = new ArrayList<>();
        for (JsonNode node : nodes) {
            MatchApiJson matchApiJson = apiMapper.createMatchFromJsonNode(node);
            Match match = matchMapper.mapFromMatchApiJson(matchApiJson);
            matchService.saveMatchWithAdmin(match);
            matches.add(match);
        }
        return matches;
    }

    private HttpRequest buildHttpRequest(String league) {
        String selectedLeague = League.fromShortCode(league);
        return HttpRequest.newBuilder()
                .uri(URI.create(oddsApiConfig.getUrl() + selectedLeague + "/odds/?apiKey=" + oddsApiConfig.getApiKey() + "&regions=eu"))
                .header("Accept", "application/json")
                .GET()
                .build();
    }
    private HttpResponse<String> sendRequest(HttpRequest request) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
    private List<Match> handleResponse(HttpResponse<String> response) throws IOException {
        if (response.statusCode() == 200) {
            String responseBody = response.body();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode[] jsonNodes = objectMapper.readValue(responseBody, JsonNode[].class);
            List<JsonNode> jsonNodeList = Arrays.asList(jsonNodes);
            return processAndSaveMatches(jsonNodeList);
        } else {
            log.warn("Error: Unable to fetch odds API response. Status code: " + response.statusCode());
            return Collections.emptyList();
        }
    }
}
