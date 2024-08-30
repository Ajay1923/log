
package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LogAnalyzerService {

    private static final String LOG_FILE_PATH = "logs/application.log";
    
    public Map<String, Integer> analyzeLogFile() {
        Map<String, Integer> exceptionCounts = new HashMap<>();
        exceptionCounts.put("NullPointerException", 0);
        exceptionCounts.put("SchedulerException", 0);
        exceptionCounts.put("AccessException", 0);
        exceptionCounts.put("InvalidFormatException", 0);
        exceptionCounts.put("CloudClientException", 0);
        exceptionCounts.put("ValidationException", 0);
        exceptionCounts.put("SuperCsvException", 0);
        exceptionCounts.put("Other", 0);

        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                analyzeLine(line, exceptionCounts);
            }
        } catch (IOException e) {
            log.error("Error reading log file", e);
        }

        return exceptionCounts;
    }

    private void analyzeLine(String line, Map<String, Integer> exceptionCounts) {
        if (line.contains("NullPointerException")) {
            exceptionCounts.put("NullPointerException", exceptionCounts.get("NullPointerException") + 1);
        } else if (line.contains("SchedulerException")) {
            exceptionCounts.put("SchedulerException", exceptionCounts.get("SchedulerException") + 1);
        } else if (line.contains("AccessException")) {
            exceptionCounts.put("AccessException", exceptionCounts.get("AccessException") + 1);
        } else if (line.contains("InvalidFormatException")) {
            exceptionCounts.put("InvalidFormatException", exceptionCounts.get("InvalidFormatException") + 1);
        } else if (line.contains("CloudClientException")) {
            exceptionCounts.put("CloudClientException", exceptionCounts.get("CloudClientException") + 1);
        } else if (line.contains("ValidationException")) {
            exceptionCounts.put("ValidationException", exceptionCounts.get("ValidationException") + 1);
        } else if (line.contains("SuperCsvException")) {
            exceptionCounts.put("SuperCsvException", exceptionCounts.get("SuperCsvException") + 1);
        } else {
            exceptionCounts.put("Other", exceptionCounts.get("Other") + 1);
        }
    }
}
