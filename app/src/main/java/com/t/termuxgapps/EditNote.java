package com.t.termuxgapps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import java.util.HashSet;

import static com.t.termuxgapps.NoteActivity_app.notes;
import static com.t.termuxgapps.NoteActivity_app.set;

public class EditNote extends AppCompatActivity implements TextWatcher {

    int noteId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

// StatusBar Color
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#292B3D"));

        EditText editText = (EditText) findViewById(R.id.editText);
        EditText editTitle = (EditText) findViewById(R.id.editTitle);


        Intent i = getIntent();
        noteId = i.getIntExtra("noteId",-1);

        if (noteId != -1){

            String fillerText = notes.get(noteId);
            editText.setText(fillerText);
            editTitle.setText(fillerText);

        }

        editText.addTextChangedListener(this);
        editTitle.addTextChangedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getApplicationContext(),NoteActivity_app.class);
                startActivity(mainIntent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        notes.set(noteId, String.valueOf(charSequence));
        NoteActivity_app.arrayAdapter.notifyDataSetChanged();

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.t.termuxgapps", Context.MODE_PRIVATE);
        if (set == null){

            set = new HashSet<String>();

        }else{

            set.clear();

        }
        set.clear();
        set.addAll(notes);
        sharedPreferences.edit().remove("notes").apply();
        sharedPreferences.edit().putStringSet("notes",set).apply();

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
