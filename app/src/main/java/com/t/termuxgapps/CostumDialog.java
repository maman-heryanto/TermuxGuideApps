package com.t.termuxgapps;

import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        Button close = (Button) view.findViewById(R.id.btn_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(getActivity());
                dialog.setCancelable(true);

                View view  = getActivity().getLayoutInflater().inflate(R.layout.layout_costum_dialog, null);
                dialog.setContentView(view);


                dialog.dismiss();
                getDialog().dismiss();
            }
        });

        return view;

    }

}
