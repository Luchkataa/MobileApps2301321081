package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BrewDao {

    @Insert
    void insert(Brew brew);

    @Update
    void update(Brew brew);

    @Delete
    void delete(Brew brew);

    @Query("SELECT * FROM brew_table ORDER BY name ASC")
    LiveData<List<Brew>> getAllBrews();

    @Query("SELECT * FROM brew_table WHERE id = :brewId")
    Brew getBrewById(int brewId);

    @Query("DELETE FROM brew_table")
    void deleteAllBrews();
}