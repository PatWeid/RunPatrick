package com.example.runpatrick.model.database;

import android.app.Application;
import android.location.Location;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.core.app.ApplicationProvider;

import com.example.runpatrick.model.datastructure.Occupation;
import com.example.runpatrick.model.datastructure.OccupationFactory;
import com.example.runpatrick.util.PojoConverter;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RepositoryTestWithUtil {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Test
    public void insertAndLoadWithUtil() throws InterruptedException {
        Application application = ApplicationProvider.getApplicationContext();
        Repository repository = new RepositoryImpl(application);
        List<Location> locationList = new ArrayList<>();
        Date startDate = new Date(200000000L);
        Date endDate = new Date(500L);
        Occupation original = OccupationFactory.produceOccupation(locationList, startDate, endDate);
        repository.insert(PojoConverter.convertToPojo(original));
        List<OccupationPojo> loadedPojos = LiveDataTestUtil.getOrAwaitValue(repository.getAllOccupations());

        Occupation loadedOccupation = PojoConverter.convertToOccupation(loadedPojos.get(0));

        assertEquals(loadedOccupation.getStartDate(), startDate);
    }

}
