package com.example.runpatrick.model.datastructure;

import android.location.Location;

import java.util.Date;
import java.util.List;

class OccupationImpl implements Occupation {
    private final List<Location> locationList;
    private final Date startDate;
    private final Date endDate;

    private final AltitudeCalculator altitudeCalculator;
    private final DistanceCalculator distanceCalculator;
    private final SpeedCalculator speedCalculator;


    OccupationImpl(List<Location> locationList, Date startDate, Date endDate) {
        this.locationList = locationList;
        this.startDate = startDate;
        this.endDate = endDate;

        this.altitudeCalculator = new AltitudeCalculator();
        this.distanceCalculator = new DistanceCalculator();
        this.speedCalculator = new SpeedCalculator();
    }

    @Override
    public List<Location> getLocationList() {
        return this.locationList;
    }

    @Override
    public Date getStartDate() {
        return this.startDate;
    }

    @Override
    public Date getEndDate() {
        return this.endDate;
    }

    @Override
    public int getPosAltitude() {
        return this.altitudeCalculator.calculatePosAltitude(this.locationList);
    }

    @Override
    public int getNegAltitude() {
        return this.altitudeCalculator.calculateNegAltitude(this.locationList);
    }

    @Override
    public double getDistanceInKilometers() {
        return (this.distanceCalculator.calculateDistanceInMeters(this.locationList)) / 1000;
    }

    @Override
    public long getOccupationTimeInSeconds() {
        return ((getEndDate().getTime()) - (getStartDate().getTime())) / 1000;
    }

    @Override
    public int[] getSpeed() {
        return this.speedCalculator.calculateSpeedInMinPerKilometer(this.getDistanceInKilometers(), this.getOccupationTimeInSeconds());
    }
}
