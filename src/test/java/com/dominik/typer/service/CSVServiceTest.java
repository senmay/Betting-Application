package com.dominik.typer.service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import static org.CSV.MyCsvService.getSheetsService;

class CSVServiceTest {

    private static Sheets sheetsService;
    private static String SPREADSHEET_ID = "1O9wdU6vJuirgdrzm3GMV5_VVaI81XjdrWbgWG-OZfQ4";

    @BeforeEach
    public void setup() throws GeneralSecurityException, IOException {
        sheetsService = getSheetsService();
    }
    @Test
    void whenWriteSheet_thenReadSheetOk() throws IOException {
        ValueRange body = new ValueRange()
                .setValues(Arrays.asList(
                        Arrays.asList("Expenses January"),
                        Arrays.asList("books", "30"),
                        Arrays.asList("pens", "10"),
                        Arrays.asList("Expenses February"),
                        Arrays.asList("clothes", "20"),
                        Arrays.asList("shoes", "5")));
        UpdateValuesResponse result = sheetsService.spreadsheets().values()
                .update(SPREADSHEET_ID, "A1", body)
                .setValueInputOption("RAW")
                .execute();
    }

    @Test
    void readValues() throws IOException {
        String range = "A1:B6";
        ValueRange response = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID, range)
                .execute();
        System.out.println(response);
    }


}