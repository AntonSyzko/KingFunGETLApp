package com.king.fung.services;

import com.king.fung.services.impl.ExtractTransformLoadServiceImpl;
import com.king.fung.services.impl.FilesAccessibilityPreCheckServiceImpl;
import com.king.fung.services.impl.InputDirectoryProcessingServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InputDirectoryProcessingServiceImplTest {

    @Mock
    private FilesAccessibilityPreCheckServiceImpl filesAccessibilityPreCheckService;
    @Mock
    private ExtractTransformLoadServiceImpl extractTransformLoadService;

    @InjectMocks
    private InputDirectoryProcessingServiceImpl underTest;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void setUp() {
        tempFolder.delete();
    }

    @After
    public void tearDown() {
        tempFolder.delete();
    }

    @Test
    public void processDirectoryFiles() throws IOException {
        underTest.processDirectoryFiles("input", "output");
        verify(filesAccessibilityPreCheckService).preCheckInputFilesDirectory("input");
        verify(filesAccessibilityPreCheckService).preCheckOutputFilesDirectory("output");
        verify(extractTransformLoadService, times(0)).extractTransformLoadFiles("input", "output");
    }

    @Test(expected = IOException.class)
    public void processDirectoryFilesTest() throws IOException {
        final File tempInputDirectory = tempFolder.newFolder("input");
        final File tempOutputDirectory = tempFolder.newFolder("output");

        underTest.processDirectoryFiles(tempInputDirectory.getPath(), tempOutputDirectory.getPath());
        verify(filesAccessibilityPreCheckService).preCheckInputFilesDirectory(tempInputDirectory.getPath());
        verify(filesAccessibilityPreCheckService).preCheckOutputFilesDirectory(tempOutputDirectory.getPath());
        verify(extractTransformLoadService).extractTransformLoadFiles(tempInputDirectory.getPath(), tempOutputDirectory.getPath());
    }
}