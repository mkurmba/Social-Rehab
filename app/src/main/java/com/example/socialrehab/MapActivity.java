package com.example.socialrehab;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private final int FINE_PERMISSION_CODE = 1;
    Location currentLocation;
    // FusedLocationProviderClient;
    private LocationManager locationManager;
    private LocationListener locationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

    }

    @Override public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Check if permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            // Request location updates
                        // Add markers for parks (you can replace this with your own park locations)
                        LatLng park1 = new LatLng(43.04173,-76.1271196);
                        mMap.addMarker(new MarkerOptions().position(park1).title("Thorden Park"));

                        LatLng park2 = new LatLng(43.029115, -76.117313);
                        mMap.addMarker(new MarkerOptions().position(park2).title("Barry Park"));

                        LatLng park3 = new LatLng(43.026337, -76.113022);
                        mMap.addMarker(new MarkerOptions().position(park3).title("Barry Park"));

                        LatLng park4 = new LatLng(43.035460, -76.121956);
                        mMap.addMarker(new MarkerOptions().position(park4).title("Westminster Park"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(park4, 15f));

        } else {
            // Request location permission if not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
    }

}
