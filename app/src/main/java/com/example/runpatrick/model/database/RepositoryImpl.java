package com.example.runpatrick.model.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RepositoryImpl implements Repository{
    private OccupationPojoDao occupationPojoDao;
    private LiveData<List<OccupationPojo>> allOccupations;

    public RepositoryImpl(Application application) {
        OccupationPojoDatabase occupationDatabase = OccupationPojoDatabase.getInstance(application);
        this.occupationPojoDao = occupationDatabase.occupationPojoDao();
        this.allOccupations = occupationPojoDao.getAllOccupations();
    }

    //insert, update, delete und deleteAll sind DB Operationen
    //-> dürfen nicht im Main Thread ausgeführt werden
    //-> Asynctask
    //die folgenden Methoden sind diejenigen, die das Repository nach außen gibt und somit die DB abstrahiert
    //-> AbstractionLayer
    public void insert(OccupationPojo occupation){
        new InsertOccupationAsyncTask(occupationPojoDao).execute(occupation);
    }

    public void delete(OccupationPojo occupation){
        new DeleteOccupationAsyncTask(occupationPojoDao).execute(occupation);
    }

    //keine DB Operation -> kann so implementiert werden
    public LiveData<List<OccupationPojo>> getAllOccupations(){
        return allOccupations;
    }


    private static class InsertOccupationAsyncTask extends AsyncTask<OccupationPojo, Void, Void> {
        private OccupationPojoDao occupationPojoDao;

        private InsertOccupationAsyncTask(OccupationPojoDao occupationPojoDao){
            this.occupationPojoDao = occupationPojoDao;
        }


        @Override
        protected Void doInBackground(OccupationPojo... occupationPojos) {
            occupationPojoDao.insert(occupationPojos[0]);
            return null;
        }
    }

    private static class DeleteOccupationAsyncTask extends AsyncTask<OccupationPojo, Void, Void> {
        private OccupationPojoDao occupationPojoDao;

        private DeleteOccupationAsyncTask(OccupationPojoDao occupationPojoDao){
            this.occupationPojoDao = occupationPojoDao;
        }


        @Override
        protected Void doInBackground(OccupationPojo... occupationPojos) {
            occupationPojoDao.delete(occupationPojos[0]);
            return null;
        }
    }
}
