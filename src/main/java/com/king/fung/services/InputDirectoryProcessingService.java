package com.king.fung.services;

import java.io.IOException;

public interface InputDirectoryProcessingService {

    void processDirectoryFiles(String pathToInput, String pathToOutput) throws IOException;
}
