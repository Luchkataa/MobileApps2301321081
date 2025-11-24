package com.example.myapplication;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BrewViewModel extends AndroidViewModel {

    private final BrewRepository repository;
    private final LiveData<List<Brew>> allBrews;

    public BrewViewModel(Application application) {
        super(application);
        repository = new BrewRepository(application);
        allBrews = repository.getAllBrews();
    }

    public LiveData<List<Brew>> getAllBrews() {
        return allBrews;
    }

    public void insert(Brew brew) {
        repository.insert(brew);
    }

    // UPDATE
    public void update(Brew brew) {
        repository.update(brew);
    }

    public void delete(Brew brew) {
        repository.delete(brew);
    }
}