package com.king.fung.services.impl;

import com.king.fung.services.FilesAccessibilityPreCheckService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;

public class FilesAccessibilityPreCheckServiceImpl implements FilesAccessibilityPreCheckService {
    @Override
    public void preCheckInputFilesDirectory(String pathToInputFilesLocation) throws FileNotFoundException {
        Path path = Paths.get(pathToInputFilesLocation);
        if (Files.notExists(path) && !Files.isDirectory(path)) {
            throw new FileNotFoundException(String.format("Provided input directory %s does not exist.", pathToInputFilesLocation));
        }
    }

    @Override
    public void preCheckOutputFilesDirectory(String pathToOutputFilesDirectory) throws IOException {
        Path path = Paths.get(pathToOutputFilesDirectory);
        if (Files.exists(path) && Files.isDirectory(path)) {
            throw new FileAlreadyExistsException(String.format("Provided output directory %s already exists.", pathToOutputFilesDirectory));
        } else {
            Files.createDirectory(Paths.get(pathToOutputFilesDirectory));
        }
    }
}

