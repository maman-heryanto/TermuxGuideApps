package com.t.termuxgapps;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.squareup.picasso.Picasso;
import com.t.termuxgapps.firebase.tools;
import com.t.termuxgapps.viewholder.ToolsViewHolder;


public class MainTool extends Fragment {


    public MainTool(){}
    RelativeLayout view;

    private RecyclerView mToolsList;
    private DatabaseReference mDatabase;

    FirebaseRecyclerAdapter<tools, ToolsViewHolder> firebaseRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.main_tool, container, false);
        getActivity().setTitle("All Tools Termux");

        //fab show
        FloatingActionButton floatingActionButton = ((MainActivity) getActivity()).getFloatingActionButton();
        if (floatingActionButton != null) {
            floatingActionButton.show();
        }

        mDatabase = FirebaseDatabase.getInstance().getReference().child("tools");
        mDatabase.keepSynced(true);



        mToolsList = (RecyclerView) view.findViewById(R.id.myrecycleview);
        mToolsList.setHasFixedSize(true);
        mToolsList.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        onStart();

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<tools, ToolsViewHolder>
                (tools.class, R.layout.content_tools, ToolsViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(ToolsViewHolder ToolsViewHolder, tools model, int position) {

                ToolsViewHolder.setTitle(model.getTitle());
                ToolsViewHolder.setDesc(model.getDesc());
                ToolsViewHolder.setImage(getActivity().getApplicationContext(), model.getImage());

                ToolsViewHolder.setOnClickListener(new ToolsViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent tools = new Intent(getActivity(), ToolDetail.class);
                        tools.putExtra("toolsId", firebaseRecyclerAdapter.getRef(position).getKey());
                        Toast.makeText(getActivity(), "TAMPIL DETAILS" + position, Toast.LENGTH_SHORT).show();
                        startActivity(tools);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(getActivity(), "Item long clicked at " + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        mToolsList.setAdapter(firebaseRecyclerAdapter);
    }

}
