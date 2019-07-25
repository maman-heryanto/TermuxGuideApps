package com.t.termuxgapps;


import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.t.termuxgapps.database.NoteDb;

public class note_add extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String NoteId;

    EditText title,content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);
        // StatusBar Color
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#292B3D"));

        title = (EditText)findViewById(R.id.txt_title);
        content = (EditText)findViewById(R.id.txt_content_note);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("Note");
        NoteId = mFirebaseDatabase.push().getKey();
    }

    public void addNote(String title,String content) {
        NoteDb noteDb = new NoteDb (title,content);
        mFirebaseDatabase.child("NoteDb").child(NoteId).setValue(noteDb);
    }

    public void  updateNote(String title,String content){
        mFirebaseDatabase.child("NoteDb").child(NoteId).child("title").setValue(title);
        mFirebaseDatabase.child("NoteDb").child(NoteId).child("content").setValue(content);
    }

    public void insertData(View view)
    {
        addNote(title.getText().toString().trim(),content.getText().toString().trim());
    }

    public void updateData(View view)
    {
        updateNote(title.getText().toString().trim(),content.getText().toString().trim());
    }
    public void readData(View view)
    {
        mFirebaseDatabase.child("NoteDb").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        String dbtitle = ds.child("title").getValue(String.class);
                        String dbcontent = ds.child("content").getValue(String.class);
                        Log.d("TAG", dbtitle + "/" + dbcontent);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
