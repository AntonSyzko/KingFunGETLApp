package com.king.fung.services;

import com.king.fung.events.TrackingEvent;

public interface ExtractTransformLoadService {

    String gameInstallationLogFileName = "gameinstallation.csv";
    String gameStartLogFileName = "gamestart.csv";
    String gamePurchaseLogFileName = "gamepurchase.csv";
    String logEntriesPipeDelimiterRegex = "\\|";

    void extractTransformLoadFiles(String pathToInputLogFile, String pathToOutput);

    String defineLogFileType(String trackingEventName);

    void writeToTrackingEventLogFile(TrackingEvent modified, String pathToOutput, String fileEnding);

}
