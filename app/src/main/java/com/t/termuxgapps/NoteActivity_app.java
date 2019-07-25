package com.t.termuxgapps;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NoteActivity_app extends AppCompatActivity {

    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    static Set<String> set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_app);

        // StatusBar Color
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#292B3D"));

        ListView listView = (ListView) findViewById(R.id.listView_note_app);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.t.termuxgapps", Context.MODE_PRIVATE);

        set = sharedPreferences.getStringSet("notes",null);
        notes.clear();

        if (set != null){

            notes.addAll(set);

        }else{

            notes.add("Example Note");
            set = new HashSet<String>();
            set.addAll(notes);
            sharedPreferences.edit().remove("notes").apply();
            sharedPreferences.edit().putStringSet("notes",set).apply();

        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,notes);


        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), EditNote.class);
                intent.putExtra("noteId",position);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
                new AlertDialog.Builder(NoteActivity_app.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are You Sure?")
                        .setMessage("Do You Want To Delete Item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                                notes.remove(position);
                                SharedPreferences sharedPreferences = getApplication().getSharedPreferences("com.t.termuxgapps", Context.MODE_PRIVATE);

                                if (set == null){

                                    set = new HashSet<String>();

                                }else{

                                    set.clear();

                                }
                                set.clear();
                                set.addAll(notes);

                                sharedPreferences.edit().remove("notes").apply();
                                sharedPreferences.edit().putStringSet("notes",set).apply();
                                arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();

            }
        });
        //btn ADD note
        ImageView btnAdd = (ImageView) findViewById(R.id.btnAddNoteApp);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNote();
            }

            private void goToNote() {
                notes.add("");
                SharedPreferences sharedPreferences = getApplication().getSharedPreferences("com.t.termuxgapps", Context.MODE_PRIVATE);
                if (set == null){

                    set = new HashSet<String>();

                }else{

                    set.clear();

                }
                set.clear();
                set.addAll(notes);

                sharedPreferences.edit().remove("notes").apply();
                sharedPreferences.edit().putStringSet("notes",set).apply();
                arrayAdapter.notifyDataSetChanged();

                Intent intent = new Intent(NoteActivity_app.this, EditNote.class);
                intent.putExtra("noteId",notes.size()-1);
                startActivity(intent);

            }
        });

    }
}
