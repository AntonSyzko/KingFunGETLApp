package com.king.fung.services;

import com.king.fung.services.impl.InvalidRecordProcessingServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class InvalidRecordProcessingServiceImplTest {

    @InjectMocks
    private InvalidRecordProcessingServiceImpl underTest;

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
    public void processFilesToInvalidLogStorage() throws IOException {
        underTest.processFilesToInvalidLogStorage("gamestart|jane|2|abcdefg", "invalid.log");
    }

    @Test
    public void isLogRowEntryValidWrongDataTypeTest() {
        String[] logRow = {"gamestart", "jane", "2", "abcdefg"};
        boolean isInvalidRow = underTest.isLogRowEntryValid(logRow);
        assertTrue(isInvalidRow);
    }

    @Test
    public void isLogRowEntryValidGameTrackingEventTest() {
        String[] logRow = {"gamesetup", "jane", "2", "123456789"};
        boolean isInvalidRow = underTest.isLogRowEntryValid(logRow);
        assertTrue(isInvalidRow);
    }

    @Test
    public void isLogRowEntryTooFewFieldsTest() {
        String[] logRow = {"gameinstallation", "jane", "123456789"};
        boolean isInvalidRow = underTest.isLogRowEntryValid(logRow);
        assertTrue(isInvalidRow);
    }

    @Test
    public void isLogRowEntryTooManyFieldsTest() {
        String[] logRow = {"gameinstallation", "jane", "1", "1", "123456789"};
        boolean isInvalidRow = underTest.isLogRowEntryValid(logRow);
        assertTrue(isInvalidRow);
    }

    @Test
    public void isLogRowEntryUnknownGameTest() {
        String[] logRow = {"gamepurchase", "jane", "4", "123456789"};
        boolean isInvalidRow = underTest.isLogRowEntryValid(logRow);
        assertTrue(isInvalidRow);
    }

    @Test
    public void isLogRowEntryValidGamePurchaseTest() {
        String[] logRow = {"gamepurchase", "jane", "1", "1 Free Life", "123456789"};
        boolean isInvalidRow = underTest.isLogRowEntryValid(logRow);
        assertFalse(isInvalidRow);
    }

    @Test
    public void isLogRowEntryValidGameStartTest() {
        String[] logRow = {"gamestart", "jane", "1", "123456789"};
        boolean isInvalidRow = underTest.isLogRowEntryValid(logRow);
        assertFalse(isInvalidRow);
    }
}