package com.t.termuxgapps;


import android.annotation.SuppressLint;
import android.app.Dialog;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import android.support.v4.app.DialogFragment;
import android.widget.TextView;
import android.widget.Toast;


import de.hdodenhof.circleimageview.CircleImageView;

public class MainAbout extends Fragment {

    public MainAbout(){}
    RelativeLayout view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.main_about, container, false);
        getActivity().setTitle("About");

        //fab hide
        FloatingActionButton floatingActionButton = ((MainActivity) getActivity()).getFloatingActionButton();
        if (floatingActionButton != null) {
            floatingActionButton.hide();
        }


        CardView about = (CardView) view.findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(getActivity());
                dialog.setCancelable(true);

                View view  = getActivity().getLayoutInflater().inflate(R.layout.layout_costum_dialog, null);
                dialog.setContentView(view);


                dialog.show();
            }
        });

        //maps
        CardView btnmaps = (CardView) view.findViewById(R.id.findme);
        btnmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMap();
            }

            private void goToMap() {
                Intent intent = new Intent(getActivity().getApplication(), MapActivity.class);
                startActivity(intent);
            }
        });

        CircleImageView mail = (CircleImageView) view.findViewById(R.id.linkMail);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMail();
            }

            private void goToMail() {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:Mnifa8916a@gmail.com"));
                startActivity(intent);
            }
        });

        CircleImageView fb = (CircleImageView) view.findViewById(R.id.linkFb);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFb();
            }

            private void goToFb() {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/search/top/?q=termux"));
                startActivity(browserIntent);
            }
        });



        return view;
    }
}
