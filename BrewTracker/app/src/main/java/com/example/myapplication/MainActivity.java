package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private BrewViewModel brewViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final BrewListAdapter adapter = new BrewListAdapter(recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        brewViewModel = new ViewModelProvider(this).get(BrewViewModel.class);

        brewViewModel.getAllBrews().observe(this, brews -> {
            adapter.setBrews(brews);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Brew testBrew = new Brew("Test Brew " + System.currentTimeMillis(), "Notes", 4.0f);
            brewViewModel.insert(testBrew);
        });
    }
}