package com.king.fung.events;

public class TrackingEvent {

    private String eventName;
    private String userId;
    private Integer gameId;
    private String description;
    private Long timestamp;

    public TrackingEvent(String eventName, String userId, Integer gameId, Long timestamp) {
        this.eventName = eventName;
        this.userId = userId;
        this.gameId = gameId;
        this.timestamp = timestamp;
    }

    public TrackingEvent(String eventName, String userId, Integer gameId, String description, Long timestamp) {
        this.eventName = eventName;
        this.userId = userId;
        this.gameId = gameId;
        this.description = description;
        this.timestamp = timestamp;
    }

    public String getEventName() {
        return eventName;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public String getDescription() {
        return description;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class TrackingEventBuilder {
        private String eventName;
        private String userId;
        private Integer gameId;
        private String description;
        private Long timestamp;

        public TrackingEventBuilder() {
        }

        public TrackingEventBuilder setEventName(String eventName) {
            this.eventName = eventName;
            return this;
        }

        public TrackingEventBuilder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public TrackingEventBuilder setGameId(Integer gameId) {
            this.gameId = gameId;
            return this;
        }

        public TrackingEventBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public TrackingEventBuilder setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public TrackingEvent build() {

            return new TrackingEvent(eventName, userId, gameId, description, timestamp);
        }
    }
}
