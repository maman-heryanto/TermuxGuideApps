package com.t.termuxgapps;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import com.t.termuxgapps.firebase.network;


public class MainNetworking extends Fragment {

    public MainNetworking(){}
    RelativeLayout view;

    private RecyclerView mNetworkList;
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.main_networking, container, false);
        getActivity().setTitle("Networking Commands");

        //fab show
        FloatingActionButton floatingActionButton = ((MainActivity) getActivity()).getFloatingActionButton();
        if (floatingActionButton != null) {
            floatingActionButton.show();
        }

        mDatabase = FirebaseDatabase.getInstance().getReference().child("network");
        mDatabase.keepSynced(true);

        mNetworkList = (RecyclerView) view.findViewById(R.id.myrecycleviewNetwork);
        mNetworkList.setHasFixedSize(true);
        mNetworkList.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        final FirebaseRecyclerAdapter<network, NetworkViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<network, NetworkViewHolder>
                (network.class, R.layout.content_network, NetworkViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(NetworkViewHolder viewHolder, network model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
            }
        };
        mNetworkList.setAdapter(firebaseRecyclerAdapter);
    }


    public static class NetworkViewHolder extends RecyclerView.ViewHolder
    {
        View mview;

        public NetworkViewHolder(View itemView){
            super(itemView);
            mview = itemView;
        }

        public void setTitle(String title) {
            TextView post_title =(TextView) mview.findViewById(R.id.post_title_network);
            post_title.setText(title);
        }

        public void setDesc(String desc) {
            TextView post_desc =(TextView) mview.findViewById(R.id.post_desc_network);
            post_desc.setText(desc);
        }
    }
}

