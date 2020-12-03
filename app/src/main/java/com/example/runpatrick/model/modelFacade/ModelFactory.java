package com.example.runpatrick.model.modelFacade;

import android.app.Application;

import com.example.runpatrick.model.database.RepositoryImpl;
import com.example.runpatrick.model.datastructure.DistanceCalculator;
import com.example.runpatrick.model.occupationMaker.OccupationMakerImpl;


public class ModelFactory {
    public static ModelFacade produce(Application application) {
        ModelFacadeImpl modelFacade = new ModelFacadeImpl();
        modelFacade.setDistanceCalculator(new DistanceCalculator());
        modelFacade.setOccupationMaker(new OccupationMakerImpl());
        modelFacade.setRepository(new RepositoryImpl(application));
        modelFacade.setTimeCalculator(new TimeCalculatorImpl());

        return modelFacade;
    }
}
