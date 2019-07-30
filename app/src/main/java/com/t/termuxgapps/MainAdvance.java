package com.t.termuxgapps;

import android.app.Fragment;
import android.content.Context;
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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.t.termuxgapps.firebase.advance;


public class MainAdvance extends Fragment {

    public MainAdvance() {
    }

    RelativeLayout view;

    private RecyclerView mAdvanceList;
    private DatabaseReference mDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.main_advance, container, false);
        getActivity().setTitle("Advance Commands");

        //fab show
        FloatingActionButton floatingActionButton = ((MainActivity) getActivity()).getFloatingActionButton();
        if (floatingActionButton != null) {
            floatingActionButton.show();
        }

        mDatabase = FirebaseDatabase.getInstance().getReference().child("advance");
        mDatabase.keepSynced(true);

        mAdvanceList = (RecyclerView) view.findViewById(R.id.myrecycleviewAdvance);
        mAdvanceList.setHasFixedSize(true);
        mAdvanceList.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        final FirebaseRecyclerAdapter<advance, AdvanceViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<advance, AdvanceViewHolder>
                (advance.class, R.layout.content_advance, AdvanceViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(AdvanceViewHolder AdvanceviewHolder, advance model, int position) {
                AdvanceViewHolder.setTitle(model.getTitle());
                AdvanceViewHolder.setDesc(model.getDesc());
            }
        };
        mAdvanceList.setAdapter(firebaseRecyclerAdapter);
    }

     public static class AdvanceViewHolder extends RecyclerView.ViewHolder
     {
         static View mview;

         public AdvanceViewHolder(View itemView){
             super(itemView);
             mview = itemView;
         }

         public static void setTitle(String title) {
             TextView post_title =(TextView) mview.findViewById(R.id.post_title_advance);
             post_title.setText(title);
         }

         public static void setDesc(String desc) {
             TextView post_desc =(TextView) mview.findViewById(R.id.post_desc_advance);
             post_desc.setText(desc);
         }
    }
}
