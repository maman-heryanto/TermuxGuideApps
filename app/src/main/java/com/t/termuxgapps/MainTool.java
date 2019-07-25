package com.t.termuxgapps;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.squareup.picasso.Picasso;
import com.t.termuxgapps.firebase.tools;


public class MainTool extends Fragment {


    public MainTool(){}
    RelativeLayout view;

    private RecyclerView mToolsList;
    private DatabaseReference mDatabase;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.main_tool, container, false);
        getActivity().setTitle("All Tools Termux");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("tools");
        mDatabase.keepSynced(true);

        mToolsList = (RecyclerView) view.findViewById(R.id.myrecycleview);
        mToolsList.setHasFixedSize(true);
        mToolsList.setLayoutManager(new LinearLayoutManager(this.getActivity()));


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        final FirebaseRecyclerAdapter<tools, ToolsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<tools, ToolsViewHolder>
                (tools.class, R.layout.content_tools, ToolsViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(ToolsViewHolder ToolsViewHolder, tools model, int position) {

                ToolsViewHolder.setTitle(model.getTitle());
                ToolsViewHolder.setDesc(model.getDesc());
                ToolsViewHolder.setImage(getActivity().getApplicationContext(), model.getImage());
            }
        };
        mToolsList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ToolsViewHolder extends RecyclerView.ViewHolder
    {
        View mview;

        public ToolsViewHolder(View itemView){
            super(itemView);
            mview = itemView;
        }

        public void setTitle(String title) {
            TextView post_title =(TextView) mview.findViewById(R.id.post_title);
            post_title.setText(title);
        }

        public void setDesc(String desc) {
            TextView post_desc =(TextView) mview.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }
        public void setImage(Context ctx, String image) {
            ImageView imageView =(ImageView) mview.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(imageView);
        }
    }
}
