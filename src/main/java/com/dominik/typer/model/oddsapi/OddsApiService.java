package com.dominik.typer.model.oddsapi;

import com.dominik.typer.configuration.OddsApiConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OddsApiService {
    private final OddsApiConfig oddsApiConfig;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public OddsApiJson fetchData(String endpoint) {
        String url = oddsApiConfig.getUrl() + endpoint + "?apiKey=" + oddsApiConfig.getApiKey();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String jsonData = response.getBody();

        OddsApiJson match = null;
        try {
            match = objectMapper.readValue(jsonData, OddsApiJson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return match;
    }

    public List<Outcome> getBetclicOdds(OddsApiJson match) {
        if (match != null && match.getBookmakers() != null) {
            for (Bookmaker bookmaker : match.getBookmakers()) {
                if ("betclic".equalsIgnoreCase(bookmaker.getKey())) {
                    for (Market market : bookmaker.getMarkets()) {
                        if ("h2h".equalsIgnoreCase(market.getKey())) {
                            return market.getOutcomes();
                        }
                    }
                }
            }
        }
        return null;
    }
}
