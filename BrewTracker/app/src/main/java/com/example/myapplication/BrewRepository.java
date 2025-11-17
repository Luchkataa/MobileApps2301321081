package com.example.myapplication;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BrewRepository {

    private BrewDao brewDao;
    private LiveData<List<Brew>> allBrews;

    public BrewRepository(Application application) {
        BrewDatabase db = BrewDatabase.getDatabase(application);
        brewDao = db.brewDao();
        allBrews = brewDao.getAllBrews();
    }

    public LiveData<List<Brew>> getAllBrews() {
        return allBrews;
    }

    public void insert(Brew brew) {
        BrewDatabase.databaseWriteExecutor.execute(() -> {
            brewDao.insert(brew);
        });
    }

    public void update(Brew brew) {
        BrewDatabase.databaseWriteExecutor.execute(() -> {
            brewDao.update(brew);
        });
    }

    public void delete(Brew brew) {
        BrewDatabase.databaseWriteExecutor.execute(() -> {
            brewDao.delete(brew);
        });
    }
}