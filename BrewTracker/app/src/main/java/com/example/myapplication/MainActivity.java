package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private BrewViewModel brewViewModel;
    public static final int NEW_BREW_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        final BrewListAdapter adapter = new BrewListAdapter(recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        brewViewModel = new ViewModelProvider(this).get(BrewViewModel.class);

        brewViewModel.getAllBrews().observe(this, adapter::setBrews);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewBrewActivity.class);

            startActivityForResult(intent, NEW_BREW_ACTIVITY_REQUEST_CODE);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_BREW_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            String name = data.getStringExtra(NewBrewActivity.EXTRA_REPLY_NAME);
            String notes = data.getStringExtra(NewBrewActivity.EXTRA_REPLY_NOTES);
            float rating = data.getFloatExtra(NewBrewActivity.EXTRA_REPLY_RATING, 0.0f);

            Brew brew = new Brew(name, notes, rating);
            brewViewModel.insert(brew);

            Toast.makeText(getApplicationContext(), "Напитката е добавена!", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Добавянето е отменено.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}