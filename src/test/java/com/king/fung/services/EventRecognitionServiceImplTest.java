package com.king.fung.services;

import com.king.fung.enums.TrackingEventType;
import com.king.fung.events.TrackingEvent;
import com.king.fung.events.TrackingEventFactory;
import com.king.fung.services.impl.EventRecognitionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class EventRecognitionServiceImplTest {

    private List<String> trackingEventLogData;

    @InjectMocks
    private EventRecognitionServiceImpl underTest;

    @Before
    public void setUp() throws Exception {
        trackingEventLogData = new ArrayList<>();
        trackingEventLogData.add("gamepurchase");
        trackingEventLogData.add("81f8f6dde88365f3928796ec7aa53f72820b06db8664f5fe76a7eb13e24546a2");
        trackingEventLogData.add("1");
        trackingEventLogData.add("Skip Level Booster");
        trackingEventLogData.add("1587583122");
    }

    @Test
    public void defineEventTypeGamePurchaseTest() throws Exception {
        TrackingEvent event = new TrackingEvent("gamepurchase", "81f8f6dde88365f3928796ec7aa53f72820b06db8664f5fe76a7eb13e24546a2", 1, "Skip Level Booster", 1587583122L);

        try (MockedStatic<TrackingEventFactory> mockedFactory = Mockito.mockStatic(TrackingEventFactory.class)) {
            mockedFactory.when(() -> TrackingEventFactory.getTrackingEvent(trackingEventLogData)).thenReturn(event);
            TrackingEvent tested = underTest.defineEventType(trackingEventLogData);
            assertEquals(tested.getEventName(), TrackingEventType.GAME_PURCHASE_EVENT.getTrackingEventType());
            assertEquals(tested.getUserId(), event.getUserId());
            assertEquals(tested.getGameId(), event.getGameId());
            assertEquals(tested.getDescription(), event.getDescription());
            assertEquals(tested.getTimestamp(), event.getTimestamp());

        }
    }

    @Test
    public void defineEventTypeGameStartTest() throws Exception {
        trackingEventLogData.set(0, "gamestart");
        trackingEventLogData.remove(3);
        TrackingEvent event = new TrackingEvent("gamestart", "81f8f6dde88365f3928796ec7aa53f72820b06db8664f5fe76a7eb13e24546a2", 1, 1587583122L);

        try (MockedStatic<TrackingEventFactory> mockedFactory = Mockito.mockStatic(TrackingEventFactory.class)) {
            mockedFactory.when(() -> TrackingEventFactory.getTrackingEvent(trackingEventLogData)).thenReturn(event);
            TrackingEvent tested = underTest.defineEventType(trackingEventLogData);
            assertEquals(tested.getEventName(), TrackingEventType.GAME_START_EVENT.getTrackingEventType());
            assertEquals(tested.getUserId(), event.getUserId());
            assertEquals(tested.getGameId(), event.getGameId());
            assertNull(tested.getDescription());
            assertEquals(tested.getTimestamp(), event.getTimestamp());
        }
    }

    @Test
    public void defineEventTypeGameInstallationTest() throws Exception {
        trackingEventLogData.set(0, "gameinstallation");
        trackingEventLogData.remove(3);
        TrackingEvent event = new TrackingEvent("gameinstallation", "81f8f6dde88365f3928796ec7aa53f72820b06db8664f5fe76a7eb13e24546a2", 1, 1587583122L);

        try (MockedStatic<TrackingEventFactory> mockedFactory = Mockito.mockStatic(TrackingEventFactory.class)) {
            mockedFactory.when(() -> TrackingEventFactory.getTrackingEvent(trackingEventLogData)).thenReturn(event);
            TrackingEvent tested = underTest.defineEventType(trackingEventLogData);
            assertEquals(tested.getEventName(), TrackingEventType.GAME_INSTALLATION_EVENT.getTrackingEventType());
            assertEquals(tested.getUserId(), event.getUserId());
            assertEquals(tested.getGameId(), event.getGameId());
            assertNull(tested.getDescription());
            assertEquals(tested.getTimestamp(), event.getTimestamp());
        }
    }
}