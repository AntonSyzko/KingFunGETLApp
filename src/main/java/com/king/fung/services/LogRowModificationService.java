package com.king.fung.services;

import com.king.fung.events.TrackingEvent;

public interface LogRowModificationService {

    String CSV_SEPARATOR = ",";

    String preProcessLogRowDataBeforeWritingToCSV(TrackingEvent trackingEvent);
}
