package com.example.runpatrick.model.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {OccupationPojo.class}, version = 2)
public abstract class OccupationPojoDatabase extends RoomDatabase {
    private static OccupationPojoDatabase instance;

    public abstract OccupationPojoDao occupationPojoDao();

    public static synchronized OccupationPojoDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    OccupationPojoDatabase.class, "occupation_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            //"autofill" aufrufen:
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private OccupationPojoDao occu;

        //das geht weil onCreate() aufgerufen wird, nachdem die DB erzeugt wurde:
        private PopulateDbAsyncTask(OccupationPojoDatabase db){
            occu = db.occupationPojoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
