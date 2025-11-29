package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class NewBrewActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_NAME = "com.example.android.brewtracker.NAME";
    public static final String EXTRA_REPLY_NOTES = "com.example.android.brewtracker.NOTES";
    public static final String EXTRA_REPLY_RATING = "com.example.android.brewtracker.RATING";
    public static final String EXTRA_REPLY_ID = "com.example.android.brewtracker.ID";

    private EditText mEditNameView;
    private EditText mEditRatingView;
    private EditText mEditNotesView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_brew);

        mEditNameView = findViewById(R.id.edit_brew_name);
        mEditRatingView = findViewById(R.id.edit_brew_rating);
        mEditNotesView = findViewById(R.id.edit_brew_notes);

        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey(EXTRA_REPLY_NAME)) {
                String name = extras.getString(EXTRA_REPLY_NAME);
                float rating = extras.getFloat(EXTRA_REPLY_RATING);
                String notes = extras.getString(EXTRA_REPLY_NOTES);

                mEditNameView.setText(name);
                mEditRatingView.setText(String.valueOf(rating));
                mEditNotesView.setText(notes);
            }
        }

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();

            if (TextUtils.isEmpty(mEditNameView.getText()) || TextUtils.isEmpty(mEditRatingView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String name = mEditNameView.getText().toString();
                String notes = mEditNotesView.getText().toString();

                float rating = 0.0f;
                try {
                    rating = Float.parseFloat(mEditRatingView.getText().toString());
                } catch (NumberFormatException e) {
                    rating = 0.0f;
                }

                if (extras != null && extras.containsKey(EXTRA_REPLY_ID)) {
                    int id = extras.getInt(EXTRA_REPLY_ID, -1);
                    replyIntent.putExtra(EXTRA_REPLY_ID, id);
                }

                replyIntent.putExtra(EXTRA_REPLY_NAME, name);
                replyIntent.putExtra(EXTRA_REPLY_NOTES, notes);
                replyIntent.putExtra(EXTRA_REPLY_RATING, rating);

                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });

        final Button buttonCancel = findViewById(R.id.button_cancel);

        buttonCancel.setOnClickListener(view -> {
            Intent replyIntent = new Intent();

            setResult(RESULT_CANCELED, replyIntent);

            finish();
        });
    }
}