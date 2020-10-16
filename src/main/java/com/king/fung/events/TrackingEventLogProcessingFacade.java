package com.king.fung.events;

import com.king.fung.services.InputDirectoryProcessingService;
import com.king.fung.services.impl.InputDirectoryProcessingServiceImpl;
import java.io.IOException;

public class TrackingEventLogProcessingFacade {

    public static void processTrackingEventsLogDirectory(String pathToInputDirectory, String pathToOutputDirectory) throws IOException {
        InputDirectoryProcessingService inputDirectoryProcessingService = new InputDirectoryProcessingServiceImpl();
        inputDirectoryProcessingService.processDirectoryFiles(pathToInputDirectory, pathToOutputDirectory);
    }
}
