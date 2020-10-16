package com.king.fung.enums;

public enum TrackingEventType {

    GAME_INSTALLATION_EVENT("gameinstallation", 4),
    GAME_START_EVENT("gamestart", 4),
    GAME_PURCHASE_EVENT("gamepurchase", 5);

    private String trackingEventType;
    private Integer numberOfRequiredFields;

    TrackingEventType(String gamepurchase, Integer numberOfRequiredFields) {
        this.trackingEventType = gamepurchase;
        this.numberOfRequiredFields = numberOfRequiredFields;
    }

    public String getTrackingEventType() {
        return trackingEventType;
    }

    public Integer getNumberOfRequiredFields() {
        return numberOfRequiredFields;
    }
}
