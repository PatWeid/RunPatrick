package com.example.runpatrick.model.database;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface Repository {
    void insert(OccupationPojo occupation);

    void delete(OccupationPojo occupation);

    LiveData<List<OccupationPojo>> getAllOccupations();
}
