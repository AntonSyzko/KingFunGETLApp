package com.king.fung.services.impl;

import com.king.fung.enums.TrackingEventType;
import com.king.fung.events.TrackingEvent;
import com.king.fung.services.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class ExtractTransformLoadServiceImpl implements ExtractTransformLoadService {

    private final InvalidRecordProcessingService invalidRecordProcessingService = new InvalidRecordProcessingServiceImpl();
    private final UserNameHashingService userNameHashingService = new UserNameHashingServiceImpl();
    private final EventRecognitionService eventRecognitionService = new EventRecognitionServiceImpl();
    private final LogRowModificationService logRowModificationService = new LogRowModificationServiceImpl();

    @Override
    public void extractTransformLoadFiles(String pathToInputLogFile, String pathToOutput) {
        try (BufferedReader br = new BufferedReader(new FileReader(pathToInputLogFile))) {

            String strLine;
            while ((strLine = br.readLine()) != null) {

                String[] rowOfLogData = strLine.split(logEntriesPipeDelimiterRegex);
                boolean isLIneInvalid = invalidRecordProcessingService.isLogRowEntryValid(rowOfLogData);
                if (isLIneInvalid) {
                    invalidRecordProcessingService.processFilesToInvalidLogStorage(strLine, pathToOutput);
                    continue;
                }
                List<String> logRowFields = Arrays.asList(rowOfLogData);

                String fileEnding = defineLogFileType(rowOfLogData[0]);

                rowOfLogData[1] = userNameHashingService.encodeUserName(rowOfLogData[1], UserNameHashingService.encoding);

                TrackingEvent event = eventRecognitionService.defineEventType(logRowFields);

                writeToTrackingEventLogFile(event, pathToOutput, fileEnding);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String defineLogFileType(String trackingEventName) {
        if (trackingEventName.equals(TrackingEventType.GAME_INSTALLATION_EVENT.getTrackingEventType())) {//todo ENUMS
            return gameInstallationLogFileName;

        } else if (trackingEventName.equals(TrackingEventType.GAME_START_EVENT.getTrackingEventType())) {
            return gameStartLogFileName;

        } else if (trackingEventName.equals(TrackingEventType.GAME_PURCHASE_EVENT.getTrackingEventType())) {
            return gamePurchaseLogFileName;
        } else {
            throw new IllegalArgumentException(String.format("Invalid tracking event type %s provided", trackingEventName));
        }
    }

    @Override
    public void writeToTrackingEventLogFile(TrackingEvent modified, String pathToOutput, String fileEnding) {
        Path filePath = Paths.get(pathToOutput + fileEnding);
        Charset charset = StandardCharsets.UTF_8;

        try (BufferedWriter writer = Files.newBufferedWriter(filePath, charset, Files.exists(filePath) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE)) {
            String preProcessedRow = logRowModificationService.preProcessLogRowDataBeforeWritingToCSV(modified);
            writer.write(preProcessedRow);
            writer.newLine();
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
    }
}
