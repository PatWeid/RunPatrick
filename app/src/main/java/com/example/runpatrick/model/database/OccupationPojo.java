package com.example.runpatrick.model.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "occupationPojo_table")
public class OccupationPojo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String longitudeString;
    private String latitudeString;
    private String altitudeString;
    private long startTime;
    private long endTime;

    public OccupationPojo(String longitudeString, String latitudeString, String altitudeString, long startTime, long endTime) {
        this.longitudeString = longitudeString;
        this.latitudeString = latitudeString;
        this.altitudeString = altitudeString;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getLongitudeString() {
        return longitudeString;
    }

    public String getLatitudeString() {
        return latitudeString;
    }

    public String getAltitudeString() {
        return altitudeString;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
}
