package com.king.fung;

import com.king.fung.events.TrackingEventLogProcessingFacade;

import java.io.IOException;

public class FunGLogExtractTransformLoadApplication {

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Both input and output directories for log files should be provided.");
        }
        try {
            TrackingEventLogProcessingFacade.processTrackingEventsLogDirectory(args[0], args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
