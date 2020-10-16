package com.king.fung.services;

public interface InvalidRecordProcessingService {

    String invalidLogsFileName = "invalid.log";
    Integer minSizeOfFieldsRequired = 3;

    void processFilesToInvalidLogStorage(String invalidLogRowEntry, String invalidLogsFilePath);
    boolean isLogRowEntryValid(String[] rowOfdata);
}
