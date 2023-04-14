package com.dominik.typer.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.CSV.GoogleAuthorizeUtil.*;

@Service

public class CSVService {
    private static final String APPLICATION_NAME = "euro";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
        Credential credential = getCredentials(GoogleNetHttpTransport.newTrustedTransport());
        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}
