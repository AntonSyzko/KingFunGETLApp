package com.king.fung.services.impl;

import com.king.fung.enums.FungGame;
import com.king.fung.enums.TrackingEventType;
import com.king.fung.services.InvalidRecordProcessingService;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumMap;

public class InvalidRecordProcessingServiceImpl implements InvalidRecordProcessingService {
    @Override
    public void processFilesToInvalidLogStorage(String invalidLogRowEntry, String invalidLogsFilePath) {
        Path filePath = Paths.get(invalidLogsFilePath + InvalidRecordProcessingService.invalidLogsFileName);
        Charset charset = StandardCharsets.UTF_8;

        try (BufferedWriter writer = Files.newBufferedWriter(filePath, charset, Files.exists(filePath) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE)) {
            writer.write(invalidLogRowEntry);
            writer.newLine();
        } catch (IOException ex) {
            System.err.format("I/O error: %s%n", ex);
        }
    }

    @Override
    public boolean isLogRowEntryValid(String[] rowOfdata) {
        boolean isRowDataInvalid = false;
        EnumMap<TrackingEventType, String> trackingTypes = new EnumMap<>(TrackingEventType.class);
        trackingTypes.put(TrackingEventType.GAME_INSTALLATION_EVENT, "gameinstallation");
        trackingTypes.put(TrackingEventType.GAME_PURCHASE_EVENT, "gamepurchase");
        trackingTypes.put(TrackingEventType.GAME_START_EVENT, "gamestart");

        EnumMap<FungGame, Integer> fungGames = new EnumMap<>(FungGame.class);
        fungGames.put(FungGame.FLYING_SWEETS, 1);
        fungGames.put(FungGame.CIRCUS_BUILDER, 2);
        fungGames.put(FungGame.ZOOM_RACER, 3);


        if (rowOfdata.length <= InvalidRecordProcessingService.minSizeOfFieldsRequired) {
            isRowDataInvalid = true;
        } else if (!trackingTypes.containsValue(rowOfdata[0])) {
            isRowDataInvalid = true;
        } else if (!rowOfdata[2].matches("\\d+") || Integer.parseInt(rowOfdata[2]) > fungGames.size()) {
            isRowDataInvalid = true;
        } else if (!rowOfdata[rowOfdata.length - 1].matches("\\d+")) {
            isRowDataInvalid = true;
        } else if ((rowOfdata[0].equals(TrackingEventType.GAME_PURCHASE_EVENT.getTrackingEventType())
                && rowOfdata.length < TrackingEventType.GAME_PURCHASE_EVENT.getNumberOfRequiredFields())
                || (!rowOfdata[0].equals(TrackingEventType.GAME_PURCHASE_EVENT.getTrackingEventType())
                && rowOfdata.length > TrackingEventType.GAME_INSTALLATION_EVENT.getNumberOfRequiredFields())) {
            isRowDataInvalid = true;
        }
        return isRowDataInvalid;
    }
}
