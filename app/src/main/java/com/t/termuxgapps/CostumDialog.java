package com.t.termuxgapps;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CostumDialog extends DialogFragment {

    public CostumDialog() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_costum_dialog, container, false);

        String versionName = BuildConfig.VERSION_NAME;

        TextView txtversion = (TextView)view.findViewById(R.id.txtVersi1);
        txtversion.setText(versionName);

        return view;

    }
}
