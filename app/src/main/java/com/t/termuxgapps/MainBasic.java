package com.t.termuxgapps;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.t.termuxgapps.firebase.basic;

public class MainBasic extends Fragment {

    public MainBasic(){}
    RelativeLayout view;

    private RecyclerView mBasickList;
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.main_basic, container, false);
        getActivity().setTitle("Basic Commands");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("basic");
        mDatabase.keepSynced(true);

        mBasickList = (RecyclerView) view.findViewById(R.id.myrecycleviewBasic);
        mBasickList.setHasFixedSize(true);
        mBasickList.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        final FirebaseRecyclerAdapter<basic, BasicViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<basic, BasicViewHolder>
                (basic.class, R.layout.content_basic, BasicViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(BasicViewHolder viewHolder, basic model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
            }
        };
        mBasickList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BasicViewHolder extends RecyclerView.ViewHolder
    {
        View mview;

        public BasicViewHolder(View itemView){
            super(itemView);
            mview = itemView;
        }

        public void setTitle(String title) {
            TextView post_title =(TextView) mview.findViewById(R.id.post_title_basic);
            post_title.setText(title);
        }

        public void setDesc(String desc) {
            TextView post_desc =(TextView) mview.findViewById(R.id.post_desc_basic);
            post_desc.setText(desc);
        }
    }
}
