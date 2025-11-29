package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.BrewListAdapter;
import com.example.myapplication.Brew;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements BrewListAdapter.OnBrewItemClickListener{


    private BrewViewModel brewViewModel;
    public static final int NEW_BREW_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        final BrewListAdapter adapter = new BrewListAdapter(recyclerView, this);
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
            int id = data.getIntExtra(NewBrewActivity.EXTRA_REPLY_ID, -1);

            Brew brew = new Brew(name, notes, rating);

            if (id != -1) {
                brew.setId(id);
                brewViewModel.update(brew);
                Toast.makeText(getApplicationContext(), "Напитката е редактирана!", Toast.LENGTH_SHORT).show();
            } else {
                brewViewModel.insert(brew);
                Toast.makeText(getApplicationContext(), "Напитката е добавена!", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), "Операцията е отменена.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onDeleteClick(Brew brew) {

        brewViewModel.delete(brew);

        Toast.makeText(this, "Изтрито: " + brew.getName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemClick(Brew brew) {
        Intent intent = new Intent(MainActivity.this, NewBrewActivity.class);

        intent.putExtra(NewBrewActivity.EXTRA_REPLY_ID, brew.getId());
        intent.putExtra(NewBrewActivity.EXTRA_REPLY_NAME, brew.getName());
        intent.putExtra(NewBrewActivity.EXTRA_REPLY_NOTES, brew.getNotes());
        intent.putExtra(NewBrewActivity.EXTRA_REPLY_RATING, brew.getRating());

        startActivityForResult(intent, NEW_BREW_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void onShareClick(Brew brew) {
        String shareText = String.format(
                "Пробвай тази напитка! %s: Оценка %.1f/5.0. Бележки: %s",
                brew.getName(),
                brew.getRating(),
                brew.getNotes()
        );

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Моята любима напитка!");

        startActivity(Intent.createChooser(shareIntent, "Сподели напитката чрез:"));
    }
}