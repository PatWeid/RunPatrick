package com.example.runpatrick.viewModel;

import android.app.Application;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.runpatrick.model.database.OccupationPojo;
import com.example.runpatrick.model.database.RepositoryImpl;
import com.example.runpatrick.model.datastructure.DistanceCalculator;
import com.example.runpatrick.model.modelFacade.ModelFacade;
import com.example.runpatrick.model.datastructure.Occupation;
import com.example.runpatrick.model.modelFacade.ModelFacadeImpl;
//import com.example.runpatrick.model.modelFacade.ModelFactory;
import com.example.runpatrick.model.occupationMaker.OccupationMakerImpl;

import java.util.List;

public class ViewModelImpl extends AndroidViewModel implements com.example.runpatrick.viewModel.ViewModel {
    private ModelFacade model;
    private LiveData<List<OccupationPojo>> allOccupasions = new MutableLiveData<>();
    private MutableLiveData<Double> occupationDistance = new MutableLiveData<>();
    private MutableLiveData<Long> occupationTime = new MutableLiveData<>();

    public ViewModelImpl(@NonNull Application application) {
        super(application);
        occupationDistance = new MutableLiveData<>();
        occupationTime = new MutableLiveData<>();

        //for testing
        //allOccupasions = new MutableLiveData<>();
        //model = ModelFactory.produce();
        allOccupasions = new MutableLiveData<>();
        occupationDistance = new MutableLiveData<>();

        model = new ModelFacadeImpl(new DistanceCalculator(), new OccupationMakerImpl(), new RepositoryImpl(application));
//        model.setDistanceCalculator(new DistanceCalculator());
//        modelFacade.setOccupationMaker(new OccupationMakerImpl());
//        modelFacade.setRepository(new RepositoryImpl(application));
//        model = ModelFactory.produce(application);
        allOccupasions = model.getAllOccupations();
    }

    @Override
    public LiveData<Double> getOccupationDistance() {
        return occupationDistance;
    }

    @Override
    public LiveData<List<OccupationPojo>> getAllOccupations() {
        return allOccupasions;
    }

    @Override
    public Occupation getOccupation(int index) {
        return null;
    }

    @Override
    public void startTracking() {
        this.model.startTracking();
    }

    @Override
    public void stopTracking(List<Location> locationList){
        this.model.stopTracking(locationList);
    }

    @Override
    public void update(List<Location> locationList) {
        this.model.update(locationList);
        occupationTime.setValue(model.getOccupationTime());
        occupationDistance.setValue(model.getOccupationDistance());
    }
}
