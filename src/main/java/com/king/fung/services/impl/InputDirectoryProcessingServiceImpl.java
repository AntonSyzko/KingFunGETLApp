package com.king.fung.services.impl;

import com.king.fung.services.ExtractTransformLoadService;
import com.king.fung.services.FilesAccessibilityPreCheckService;
import com.king.fung.services.InputDirectoryProcessingService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class InputDirectoryProcessingServiceImpl implements InputDirectoryProcessingService {

    private FilesAccessibilityPreCheckService filesAccessibilityPreCheckService = new FilesAccessibilityPreCheckServiceImpl();
    private ExtractTransformLoadService extractTransformLoadService = new ExtractTransformLoadServiceImpl();

    @Override
    public void processDirectoryFiles(String pathToInput, String pathToOutput) throws IOException {
        preCheckFilesAvailability(pathToInput, pathToOutput);

        try (Stream<Path> paths = Files.walk(Paths.get(pathToInput))) {
            paths.filter(Files::isRegularFile)
                    .forEach(path -> extractTransformLoadService.extractTransformLoadFiles(String.valueOf(path), pathToOutput));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void preCheckFilesAvailability(String pathToInput, String pathToOutput) throws IOException {
        filesAccessibilityPreCheckService.preCheckInputFilesDirectory(pathToInput);
        filesAccessibilityPreCheckService.preCheckOutputFilesDirectory(pathToOutput);
    }
}
