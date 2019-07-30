package com.t.termuxgapps;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.uncopt.android.widget.text.justify.JustifiedTextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainHome extends Fragment {

    public MainHome(){}
    RelativeLayout view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.main_home, container, false);
        getActivity().setTitle("Termux Apps");

        //fab hide
        FloatingActionButton floatingActionButton = ((MainActivity) getActivity()).getFloatingActionButton();
        if (floatingActionButton != null) {
            floatingActionButton.hide();
        }


        JustifiedTextView myMsg = (JustifiedTextView) view.findViewById(R.id.t1);
        myMsg.setText("Termux is an Android terminal emulator and Linux environment app that " +
                "works directly with no rooting or setup required. A minimal base system is installed " +
                "automatically - additional packages are available using the APT package manager.");

        //goto google play
        TextView gp = (TextView) view.findViewById(R.id.btn_googleplay);
        gp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGooglePlay();
            }

            private void goToGooglePlay() {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.termux"));
                startActivity(browserIntent);
            }
        });

        //goto f-droid
        TextView fdroid = (TextView) view.findViewById(R.id.btn_fdroid);
        fdroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFdroid();
            }

            private void goToFdroid() {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://f-droid.org/packages/com.termux/"));
                startActivity(browserIntent);
            }
        });

        TextView wikipedia = (TextView) view.findViewById(R.id.wiki);
        wikipedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWikipedia();
            }

            private void goToWikipedia() {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wiki.termux.com/wiki/Main_Page"));
                startActivity(browserIntent);
            }
        });


//       image
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewPageAndroid);
        MainHomeAdapter adapterView = new MainHomeAdapter(this.getActivity());
        mViewPager.setAdapter(adapterView);

        return view;


    }
}
