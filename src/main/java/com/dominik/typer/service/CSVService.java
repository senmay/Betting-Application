package com.dominik.typer.service;

import com.dominik.typer.model.Team;
import com.dominik.typer.model.csv.TeamCSV;
import com.dominik.typer.service.teampersistence.TeamService;
import com.dominik.typer.validators.GeneralValidator;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

import static org.CSV.GoogleAuthorizeUtil.getCredentials;

@Service
@Slf4j
public class CSVService {
    private static Sheets sheetsService;
    private final TeamService teamService;
    private static String SPREADSHEET_ID = "1O9wdU6vJuirgdrzm3GMV5_VVaI81XjdrWbgWG-OZfQ4";
    private static final String APPLICATION_NAME = "euro";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private final GeneralValidator validator;

    public CSVService(GeneralValidator validator, TeamService teamService) throws GeneralSecurityException, IOException {
        this.validator = validator;
        this.teamService = teamService;
        sheetsService = getSheetsService();
    }

    public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
        Credential credential = getCredentials(GoogleNetHttpTransport.newTrustedTransport());
        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    void readValues() throws IOException {
        String range = "A1:B6";
        ValueRange response = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID, range)
                .execute();
    }

    public Integer addSheetToSpreadsheet(String sheetTitle) throws IOException {
        AddSheetRequest addSheetRequest = new AddSheetRequest()
                .setProperties(new SheetProperties().setTitle(sheetTitle));

        BatchUpdateSpreadsheetRequest batchUpdateRequest = new BatchUpdateSpreadsheetRequest()
                .setRequests(Collections.singletonList(new Request().setAddSheet(addSheetRequest)));

        BatchUpdateSpreadsheetResponse batchUpdateResponse = sheetsService.spreadsheets()
                .batchUpdate(SPREADSHEET_ID, batchUpdateRequest)
                .execute();
        AddSheetResponse addSheetResponse = batchUpdateResponse.getReplies().get(0).getAddSheet();
        return addSheetResponse.getProperties().getSheetId();
    }

    public void deleteSheetFromSpreadsheet(String sheetTitle) throws IOException {
        Integer sheetId = getSheetIdByTitle(sheetTitle);
        DeleteSheetRequest deleteSheetRequest = new DeleteSheetRequest()
                .setSheetId(sheetId);

        BatchUpdateSpreadsheetRequest batchUpdateRequest = new BatchUpdateSpreadsheetRequest()
                .setRequests(Collections.singletonList(new Request().setDeleteSheet(deleteSheetRequest)));

        BatchUpdateSpreadsheetResponse batchUpdateResponse = sheetsService.spreadsheets()
                .batchUpdate(SPREADSHEET_ID, batchUpdateRequest)
                .execute();
    }

    public Integer getSheetIdByTitle(String sheetTitle) throws IOException {
        Spreadsheet spreadsheet = sheetsService.spreadsheets().get(SPREADSHEET_ID).execute();
        for (Sheet sheet : spreadsheet.getSheets()) {
            if (sheet.getProperties().getTitle().equals(sheetTitle)) {
                return sheet.getProperties().getSheetId();
            }
        }
        return null;
    }

    public List<TeamCSV> getTeamsFromGoogleSheets(String sheetTitle) throws IOException {
        String range = sheetTitle + "!A:A";
        ValueRange response = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID, range)
                .execute();

        List<List<Object>> values = response.getValues();
        List<String> allErrors = new ArrayList<>();
        Set<TeamCSV> teams = new HashSet<>();
        if (values != null) {
            for (List<Object> row : values) {
                String name = row.get(0).toString();
                TeamCSV team = new TeamCSV(name);
                List<String> errors = validator.validateObjectAndGetErrors(team);
                if (errors.isEmpty()) {
                    teams.add(team);
                } else {
                    allErrors.addAll(errors);
                }
            }
        }
        if (!allErrors.isEmpty()) {
            log.warn("Errors - not everything was imported");
        }

        return teams.stream().toList();
    }

    public void exportTeamToSpreadsheet(String sheetTitle) throws IOException {
        Integer sheetId = getSheetIdByTitle(sheetTitle);
        if (sheetId == null) {
            sheetId = addSheetToSpreadsheet(sheetTitle);
            log.warn("Sheet was created");
        }
        List<Team> teams = teamService.getTeams();
        List<List<Object>> values = new ArrayList<>();
        for (Team team : teams) {
            List<Object> row = new ArrayList<>();
            row.add(team.getName());
            values.add(row);
        }
        String range = sheetTitle + "!A1:A" + values.size();
        ValueRange response = new ValueRange().setValues(values);
        sheetsService.spreadsheets().values().update(SPREADSHEET_ID, range, response)
                .setValueInputOption("RAW").execute();
    }
}



