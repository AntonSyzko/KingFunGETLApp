package com.king.fung.services.impl;

import com.king.fung.events.TrackingEvent;

import com.king.fung.events.TrackingEventFactory;
import com.king.fung.services.EventRecognitionService;

import java.util.List;

public class EventRecognitionServiceImpl implements EventRecognitionService {
    @Override
    public TrackingEvent defineEventType(List<String> trackingEventLogData) {
        TrackingEvent event = null;
        try {
            event = TrackingEventFactory.getTrackingEvent(trackingEventLogData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }
}
