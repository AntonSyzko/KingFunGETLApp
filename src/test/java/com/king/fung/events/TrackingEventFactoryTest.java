package com.king.fung.events;


import com.king.fung.enums.TrackingEventType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TrackingEventFactoryTest {

    private List<String> trackingEventLogData;

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
    public void getPurchaseGameTrackingEventTest() throws Exception {
        TrackingEvent eventFromFactory = TrackingEventFactory.getTrackingEvent(trackingEventLogData);
        assertNotNull(eventFromFactory);
        assertEquals(eventFromFactory.getEventName(), TrackingEventType.GAME_PURCHASE_EVENT.getTrackingEventType());
        assertEquals(eventFromFactory.getUserId(), trackingEventLogData.get(1));
        assertEquals(eventFromFactory.getGameId(), Integer.valueOf(trackingEventLogData.get(2)));
        assertEquals(eventFromFactory.getDescription(), trackingEventLogData.get(3));
        assertEquals(eventFromFactory.getTimestamp(), Long.valueOf(trackingEventLogData.get(4)));
    }

    @Test
    public void getStartGAmeTrackingEventTest() throws Exception {
        trackingEventLogData.set(0, "gamestart");
        trackingEventLogData.remove(3);
        TrackingEvent eventFromFactory = TrackingEventFactory.getTrackingEvent(trackingEventLogData);
        assertNotNull(eventFromFactory);
        assertEquals(eventFromFactory.getEventName(), TrackingEventType.GAME_START_EVENT.getTrackingEventType());
        assertEquals(eventFromFactory.getUserId(), trackingEventLogData.get(1));
        assertEquals(eventFromFactory.getGameId(), Integer.valueOf(trackingEventLogData.get(2)));
        assertNull(eventFromFactory.getDescription());
        assertEquals(eventFromFactory.getTimestamp(), Long.valueOf(trackingEventLogData.get(3)));
    }
}