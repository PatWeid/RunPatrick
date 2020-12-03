package com.example.runpatrick.model.database;

import android.app.Application;
import android.content.Context;
import android.location.Location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.runpatrick.model.datastructure.Occupation;
import com.example.runpatrick.model.datastructure.OccupationFactory;
import com.example.runpatrick.util.PojoConverter;
import com.example.runpatrick.view.tracking.TrackingActivity;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RepositoryTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Test
    public void insertAndLoad(){
        Application application = ApplicationProvider.getApplicationContext();

        Repository repository = new RepositoryImpl(application);
        List<Location> locationList = new ArrayList<>();
        Date startDate = new Date(150L);
        Date endDate = new Date(500L);
        repository.insert(PojoConverter.convertToPojo(OccupationFactory.produceOccupation(locationList, startDate, endDate)));
        final OccupationPojo[] loadedPojo = new OccupationPojo[1];

        LiveData<List<OccupationPojo>> listMutableLiveData = repository.getAllOccupations();
        listMutableLiveData.observeForever(new Observer<List<OccupationPojo>>() {
            @Override
            public void onChanged(List<OccupationPojo> occupationPojos) {
                loadedPojo[0] = occupationPojos.get(0);
            }
        });
        Occupation loadedOccupation = PojoConverter.convertToOccupation(loadedPojo[0]);

        assertEquals(loadedOccupation.getStartDate(), startDate);
    }
}
