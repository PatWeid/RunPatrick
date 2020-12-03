package com.example.runpatrick.model.modelFacade;

public interface TimeCalculator {
    void startTracking();

    void update();

    Long getOccupationTime();
}
