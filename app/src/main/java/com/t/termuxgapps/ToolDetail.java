package com.t.termuxgapps;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.t.termuxgapps.firebase.tools;
import com.t.termuxgapps.viewholder.ToolsViewHolder;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ToolDetail extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference tools;

    TextView txtTitle;
    TextView txtDesc;
    TextView txtUrl;
    ImageView tool_image;
    FloatingActionButton btndetail;
    CollapsingToolbarLayout collapsingToolbarLayout;
    String tool_id = "";
    FirebaseRecyclerAdapter<tools, ToolsViewHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_detail);

//Firebase
        database = FirebaseDatabase.getInstance();
        tools = database.getReference("tools");

        //Init View
        btndetail = (FloatingActionButton) findViewById(R.id.btngithub);
        txtTitle = (TextView) findViewById(R.id.tool_name);
        txtDesc = (TextView) findViewById(R.id.tool_desc);
        tool_image = (ImageView) findViewById(R.id.img_tool);

        collapsingToolbarLayout = findViewById(R.id.collapsingImage);

        if (getIntent() != null)
            tool_id = getIntent().getStringExtra("toolsId");
        if (!tool_id.isEmpty()) {

            getdataToolid(tool_id);

        }




    }

    private void getdataToolid(final String tool_id) {
        tools.child(tool_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final tools tool = dataSnapshot.getValue(tools.class);

                //masukan gambar
                Picasso.get().load(tool.getImage()).into(tool_image);

                collapsingToolbarLayout.setTitle(tool.getTitle());

                txtTitle.setText(tool.getTitle());
                txtDesc.setText(tool.getDesc());

                FloatingActionButton fab = findViewById(R.id.btngithub);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(tool.getUrl()));
                        startActivity(intent);

                    }

                });


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}


