package com.example.runpatrick.model.modelFacade;

import android.app.Application;
import android.location.Location;

import com.example.runpatrick.model.database.RepositoryImpl;
import com.example.runpatrick.model.datastructure.DistanceCalculator;
import com.example.runpatrick.model.datastructure.Occupation;
import com.example.runpatrick.model.occupationMaker.OccupationMaker;
import com.example.runpatrick.model.occupationMaker.OccupationMakerImpl;

import java.util.List;

//public class ModelFactory {
//    public static ModelFacade produce(Application application) {
//        ModelFacadeImpl modelFacade = new ModelFacadeImpl();
//        modelFacade.setDistanceCalculator(new DistanceCalculator());
//        modelFacade.setOccupationMaker(new OccupationMakerImpl());
//        modelFacade.setRepository(new RepositoryImpl(application));
//
//        return new ModelFacadeImpl();
//    }
//}
