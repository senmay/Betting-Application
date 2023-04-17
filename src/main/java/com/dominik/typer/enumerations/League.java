package com.dominik.typer.enumerations;

import com.dominik.typer.model.exceptions.MyAppException;

public enum League {
    CL("soccer_uefa_champs_league"),
    SWE("soccer_sweden_superettan");
    private final String leagueKey;

    League(String leagueKey) {
        this.leagueKey = leagueKey;
    }

    public String getLeagueKey() {
        return leagueKey;
    }
    public static String fromShortCode(String shortCode) {
        for (League league : values()) {
            if (league.name().equalsIgnoreCase(shortCode)) {
                return league.getLeagueKey();
            }
        }
        throw new MyAppException("Invalid league short code: " + shortCode);
    }
}