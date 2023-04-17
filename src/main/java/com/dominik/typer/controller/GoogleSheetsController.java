package com.dominik.typer.controller;

import com.dominik.typer.service.GoogleSheetsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/sheet")
@RequiredArgsConstructor
@Slf4j
public class GoogleSheetsController {
    private final GoogleSheetsService googleSheetsService;

    @PostMapping("/new")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    void createNewSheetInSpreadsheet(@RequestParam String sheetTitle) throws IOException {
        googleSheetsService.addSheetToSpreadsheet(sheetTitle);
    }
    @DeleteMapping("/delete")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteSheetFromSpreadsheet(@RequestParam String sheetTitle) throws IOException {
        googleSheetsService.deleteSheetFromSpreadsheet(sheetTitle);
    }

    @PostMapping("/exportTeam")
    @ResponseStatus(HttpStatus.OK)
    void exportToSpreadsheet(@RequestParam String sheetTitle) throws IOException {
        googleSheetsService.exportToSpreadsheet(sheetTitle);
    }

}
