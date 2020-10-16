package com.king.fung.services;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FilesAccessibilityPreCheckService {

    void preCheckInputFilesDirectory(String pathToInputFilesLocation) throws FileNotFoundException;
    void preCheckOutputFilesDirectory(String pathToOutputFilesDirectory) throws IOException;

}
