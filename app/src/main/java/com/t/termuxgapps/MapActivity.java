package com.t.termuxgapps;

/**
 * NIM   : 10116089
 * Nama  : Maman Heryanto
 *
 */
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {


    GoogleMap map;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // StatusBar Color
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#292B3D"));

        SupportMapFragment mapFragment =(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync( this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        float zoomLevel = 16.0f; //This goes up to 21

        LatLng Mapme = new LatLng(-6.888710, 107.619857);
        map.addMarker(new MarkerOptions().position(Mapme).title("Kota Bandung\nJawa Barat \n40132"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Mapme, zoomLevel));
        map.moveCamera(CameraUpdateFactory.newLatLng(Mapme));
    }



}
