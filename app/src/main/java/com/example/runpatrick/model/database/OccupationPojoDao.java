package com.example.runpatrick.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OccupationPojoDao {
    @Insert
    void insert(OccupationPojo occupation);

    @Delete
    void delete(OccupationPojo occupation);

    @Query("SELECT * FROM occupationPojo_table ORDER BY id DESC")
    LiveData<List<OccupationPojo>> getAllOccupations();
}
