package com.example.myapplication;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Brew.class}, version = 1, exportSchema = false)
public abstract class BrewDatabase extends RoomDatabase {

    public abstract BrewDao brewDao();

    private static volatile BrewDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static BrewDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BrewDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    BrewDatabase.class, "brew_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}