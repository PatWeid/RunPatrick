package com.example.runpatrick.model.modelFacade;

import com.example.runpatrick.model.occupationMaker.OccupationMaker;
import com.example.runpatrick.model.occupationMaker.OccupationMakerImpl;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.fail;

public class OccupationMakerTest {

    @Test
    public void afds() {
        OccupationMaker occupationMaker = new OccupationMakerImpl();
        try {
            occupationMaker.stopTracking(new ArrayList<>());
        } catch (wrongSequenceException e) {
            e.printStackTrace();
        }
        try {
            occupationMaker.startTracking();
        } catch (wrongSequenceException e) {
           fail();
        }
    }

//    @Test
//    public void afdds() throws wrongSequenceException {
//        OccupationMaker occupationMaker = new OccupationMakerImpl();
//        occupationMaker.startTracking();
//        occupationMaker.stopTracking(new ArrayList<>());
//        occupationMaker.stopTracking(new ArrayList<>());
//    }
}
