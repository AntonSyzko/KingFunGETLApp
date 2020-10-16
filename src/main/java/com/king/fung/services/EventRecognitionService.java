package com.king.fung.services;

import com.king.fung.events.TrackingEvent;

import java.util.List;

public interface EventRecognitionService {

    TrackingEvent defineEventType(List<String> trackingEventLogData);
}
