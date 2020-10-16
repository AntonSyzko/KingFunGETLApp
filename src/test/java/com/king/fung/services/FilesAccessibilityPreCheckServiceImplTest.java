package com.king.fung.services;

import com.king.fung.services.impl.FilesAccessibilityPreCheckServiceImpl;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;


@RunWith(MockitoJUnitRunner.class)
public class FilesAccessibilityPreCheckServiceImplTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @InjectMocks
    private FilesAccessibilityPreCheckServiceImpl underTest;

    @After
    public void tearDown() {
        tempFolder.delete();
    }

    @Test
    public void preCheckInputFilesDirectoryTest() throws IOException {
        final File tempInputDirectory = tempFolder.newFolder("input");
        underTest.preCheckInputFilesDirectory(tempInputDirectory.getPath());
    }

    @Test(expected = FileNotFoundException.class)
    public void preCheckInputFilesDirectoryTestDoesNotExts() throws IOException {
        final File tempInputDirectory = tempFolder.newFolder("input");
        tempInputDirectory.delete();
        underTest.preCheckInputFilesDirectory(tempInputDirectory.getPath());
    }

    @Test
    public void preCheckOutputFilesDirectoryTest() throws IOException {
        final File tempOuputDirectory = tempFolder.newFolder("output");
        tempOuputDirectory.delete();
        underTest.preCheckOutputFilesDirectory(tempOuputDirectory.getPath());
    }

    @Test(expected = FileAlreadyExistsException.class)
    public void preCheckOutputFilesDirectoryAlreadyExistsTest() throws IOException {
        final File tempOutputDirectory = tempFolder.newFolder("output");
        underTest.preCheckOutputFilesDirectory(tempOutputDirectory.getPath());
    }
}