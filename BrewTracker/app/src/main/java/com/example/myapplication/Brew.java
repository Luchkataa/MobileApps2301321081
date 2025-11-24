package com.example.myapplication;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "brew_table")
public class Brew {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;

    private String notes;
    private float rating;

    public Brew(@NonNull String name, String notes, float rating) {
        this.name = name;
        this.notes = notes;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public float getRating() {
        return rating;
    }
}