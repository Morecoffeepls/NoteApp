package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {

    int mNoteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText editText = findViewById(R.id.noteEditText);

        Intent intent = getIntent();
        mNoteId = intent.getIntExtra("mNoteId",-1);

        if (mNoteId != -1){
            editText.setText(MainActivity.mNotes.get(mNoteId));
        } else {
            MainActivity.mNotes.add("");
            mNoteId = MainActivity.mNotes.size() -1;
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.mNotes.set(mNoteId, String.valueOf(s));
                MainActivity.mArrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notetakingapp", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(MainActivity.mNotes);
                sharedPreferences.edit().putStringSet("mNotes", set).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}