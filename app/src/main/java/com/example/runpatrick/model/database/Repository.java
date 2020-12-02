package com.example.runpatrick.model.database;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * an interface for the repository. It`s the abstraction layer for the database and can only deal with simple
 * datastructures. If an occupations should be safed, it has to be converted to a POJO
 * -> just use PojoConverter.convertToPojo(Occupation occupation) or
 * PojoConverter.convertToOccupation(OccupationPojo occupationPojo)
 */
public interface Repository {
    /**
     * saves a POJO to the database
     *
     * @param occupation the POJO to be saved
     */
    void insert(OccupationPojo occupation);

    /**
     * returns all POJOs saved in the database as a LiveDataObject
     *
     * @return LiveData with a list of all POJOs in the database
     */
    LiveData<List<OccupationPojo>> getAllOccupations();
}
