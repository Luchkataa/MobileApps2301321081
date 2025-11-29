package com.example.myapplication;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.concurrent.ExecutorService;

import java.util.List;

public class BrewRepository {

    private BrewDao brewDao;
    private LiveData<List<Brew>> allBrews;
    private final ExecutorService executorService;

    public BrewRepository(Application application) {
        BrewDatabase db = BrewDatabase.getDatabase(application);
        brewDao = db.brewDao();
        allBrews = brewDao.getAllBrews();
        this.executorService = BrewDatabase.databaseWriteExecutor;
    }

    public BrewRepository(BrewDao brewDao, ExecutorService executorService){
        this.brewDao = brewDao;
        this.allBrews = brewDao.getAllBrews();
        this.executorService = executorService;
    }

    public LiveData<List<Brew>> getAllBrews() {
        return allBrews;
    }

    public void insert(Brew brew) {
        executorService.execute(() -> {
            brewDao.insert(brew);
        });
    }

    public void update(Brew brew) {
        executorService.execute(() -> {
            brewDao.update(brew);
        });
    }

    public void delete(Brew brew) {
        executorService.execute(() -> {
            brewDao.delete(brew);
        });
    }
}