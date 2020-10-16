package com.king.fung.services.impl;

import com.king.fung.events.TrackingEvent;
import com.king.fung.services.LogRowModificationService;

import java.util.Objects;

public class LogRowModificationServiceImpl implements LogRowModificationService {

    @Override
    public String preProcessLogRowDataBeforeWritingToCSV(TrackingEvent trackingEvent) {
        StringBuilder oneLine = new StringBuilder();
        oneLine.append("\"" + trackingEvent.getUserId() + "\"");
        oneLine.append(CSV_SEPARATOR);
        oneLine.append("\"" + trackingEvent.getGameId() + "\"");
        oneLine.append(CSV_SEPARATOR);
        if (Objects.nonNull(trackingEvent.getDescription())) {
            oneLine.append("\"" + trackingEvent.getDescription() + "\"");
            oneLine.append(CSV_SEPARATOR);
        }
        oneLine.append("\"" + trackingEvent.getTimestamp() + "\"");

        return oneLine.toString();
    }
}
