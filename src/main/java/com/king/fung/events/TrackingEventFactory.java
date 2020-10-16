package com.king.fung.events;

import com.king.fung.enums.TrackingEventType;

import java.util.List;

public class TrackingEventFactory {

    public static TrackingEvent getTrackingEvent(List<String> eventDaraRow) throws Exception {

        if (eventDaraRow.get(0).equals(TrackingEventType.GAME_INSTALLATION_EVENT.getTrackingEventType())) {

            TrackingEvent installEvent = new TrackingEvent.TrackingEventBuilder()
                    .setEventName(TrackingEventType.GAME_INSTALLATION_EVENT.getTrackingEventType())
                    .setUserId(eventDaraRow.get(1))
                    .setGameId(Integer.valueOf(eventDaraRow.get(2)))
                    .setTimestamp(Long.valueOf(eventDaraRow.get(3)))
                    .build();
            return installEvent;

        } else if (eventDaraRow.get(0).equals(TrackingEventType.GAME_START_EVENT.getTrackingEventType())) {

            TrackingEvent startEvent = new TrackingEvent.TrackingEventBuilder().
                    setEventName(TrackingEventType.GAME_START_EVENT.getTrackingEventType())
                    .setUserId(eventDaraRow.get(1))
                    .setGameId(Integer.valueOf(eventDaraRow.get(2)))
                    .setTimestamp(Long.valueOf(eventDaraRow.get(3)))
                    .build();
            return startEvent;

        } else if (eventDaraRow.get(0).equals(TrackingEventType.GAME_PURCHASE_EVENT.getTrackingEventType())) {

            TrackingEvent purchaseEvent = new TrackingEvent.TrackingEventBuilder()
                    .setEventName(TrackingEventType.GAME_PURCHASE_EVENT.getTrackingEventType())
                    .setUserId(eventDaraRow.get(1))
                    .setGameId(Integer.valueOf(eventDaraRow.get(2)))
                    .setDescription(eventDaraRow.get(3))
                    .setTimestamp(Long.valueOf(eventDaraRow.get(4)))
                    .build();

            return purchaseEvent;
        } else {
            throw new Exception("invalid tracking event type ");
        }
    }


}
