package com.king.fung.services;

import com.king.fung.events.TrackingEvent;
import com.king.fung.services.impl.LogRowModificationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LogRowModificationServiceImplTest {

    private TrackingEvent trackingEvent;

    @InjectMocks
    private LogRowModificationServiceImpl underTest;

    @Before
    public void setUp() throws Exception {
        trackingEvent = new TrackingEvent.TrackingEventBuilder()
                .setEventName("gameinstallation")
                .setUserId("81f8f6dde88365f3928796ec7aa53f72820b06db8664f5fe76a7eb13e24546a2")
                .setGameId(1)
                .setDescription("Zoom Racer")
                .setTimestamp(123456L)
                .build();
    }

    @Test
    public void preProcessLogRowDataBeforeWritingToCSVTest() {

        String oneLine = underTest.preProcessLogRowDataBeforeWritingToCSV(trackingEvent);
        assertNotNull(oneLine);
        assertEquals(oneLine.split(",")[0], "\"" + trackingEvent.getUserId() + "\"");
        assertEquals(oneLine.split(",")[1], "\"" + trackingEvent.getGameId() + "\"");
        assertEquals(oneLine.split(",")[2], "\"" + trackingEvent.getDescription() + "\"");
        assertEquals(oneLine.split(",")[3], "\"" + trackingEvent.getTimestamp() + "\"");

    }

    @Test
    public void preProcessLogRowDataBeforeWritingToCSVStartEventTest() {
        trackingEvent.setEventName("gamestart");
        trackingEvent.setDescription(null);
        String oneLine = underTest.preProcessLogRowDataBeforeWritingToCSV(trackingEvent);
        assertNotNull(oneLine);
        assertEquals(oneLine.split(",")[0], "\"" + trackingEvent.getUserId() + "\"");
        assertEquals(oneLine.split(",")[1], "\"" + trackingEvent.getGameId() + "\"");
        assertEquals(oneLine.split(",")[2], "\"" + trackingEvent.getTimestamp() + "\"");
    }
}