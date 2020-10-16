package com.king.fung.enums;

public enum FungGame {

    FLYING_SWEETS(1),
    CIRCUS_BUILDER(2),
    ZOOM_RACER(3);

    private Integer gameNumber;

    FungGame(Integer gameNumber) {
        this.gameNumber = gameNumber;
    }

    public Integer getGameNumber() {
        return gameNumber;
    }
}
