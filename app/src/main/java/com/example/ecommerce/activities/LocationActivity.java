package com.example.ecommerce.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;

import com.example.ecommerce.R;
import com.example.ecommerce.helpers.AppActivity;
import com.example.ecommerce.models.Token;
import com.example.ecommerce.presenters.TokenPresenters;
import com.example.ecommerce.utilis.Utilities;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends AppActivity implements SearchView.OnQueryTextListener, View.OnClickListener, TokenPresenters.View, OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private Button btn_submit;
    private androidx.appcompat.widget.SearchView searchView;
    private GoogleMap map;
    private SupportMapFragment mapFragment;
    private LocationManager locationManager;
    private LocationListener locationListener;
    Marker mCurrLocationMarker;
    double lat,lon;

    LocationRequest mLocationRequest;
    Location mLastLocation;
    FusedLocationProviderClient mFusedLocationClient;
    int PERMISSION_ID = 44;
     private  LatLng userLatLng;
    private static int REQUEST_CHECK_SETTINGS = 200;



    TokenPresenters tokenPresenters;



    LocationCallback callback=new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            for (Location location : locationResult.getLocations()) {

                if (mCurrLocationMarker != null) {
                    mCurrLocationMarker.remove();
                }
                mLastLocation = location;
                //Place current location marker
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                lat=location.getLatitude();
                lon=location.getLongitude();


                mCurrLocationMarker = map.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("My Location"));
                // .anchor(0f,0f));
                // .icon(markerIcon));

                map.moveCamera(CameraUpdateFactory.newLatLng(latLng));

            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        searchView = findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(this);
        initializedView();
        initializedListners();
        checkIfUserIsLogin();

    }
    private void checkIfUserIsLogin() {
        if(Utilities.isLogin())
        {
            Intent intent=new Intent(LocationActivity.this,Dashboard.class);
            startActivity(intent);
        }
    }

@Override
    protected void initializedView() {
    checkPermissions();
        btn_submit = findViewById(R.id.btn_submit);
    mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    checkLocationSettings();







    }

    private void checkLocationSettings() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000); // two minute interval
        mLocationRequest.setFastestInterval(2000);
        // mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        Task<LocationSettingsResponse> result =
                LocationServices.getSettingsClient(this).checkLocationSettings(builder.build());

        result.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // All location settings are satisfied. The client can initialize
                // location requests here.
                // ...
            }
        });

        result.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(LocationActivity.this,
                                REQUEST_CHECK_SETTINGS);
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                    }
                }
            }
        });
    }

    @Override
    protected void initializedListners() {
        tokenPresenters=new TokenPresenters(this);
        tokenPresenters.token();
        btn_submit.setOnClickListener(this);





    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        String location=searchView.getQuery().toString();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:
                Intent intent=new Intent(LocationActivity.this,OtpSendActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                Log.e("onClick: ",lat+":"+lon );
                startActivity(intent);
                break;

        }
    }




    @Override
    public void onResponseSuccess(Token token) {
        Log.e( "onResponseSuccess: ",new GsonBuilder().create().toJson(token));
        Utilities.saveLoginResponse(token);

    }

    @Override
    public void onResponseFailure(String message) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;

//        LatLng latLng=new LatLng(27.753425899429065, 85.29464598282611);
//        map.addMarker(new MarkerOptions().position(latLng).title("Home"));
//        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));



        locationManager= (LocationManager) this.getSystemService( Context.LOCATION_SERVICE);
        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                userLatLng=new LatLng(location.getLatitude(),location.getLongitude());
                lat=location.getLatitude();
                lon=location.getLongitude();
                map.clear();
                map.addMarker(new MarkerOptions().position(userLatLng).title("Your Location"));
                map.moveCamera(CameraUpdateFactory.newLatLng(userLatLng));


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        map.setOnMapClickListener(this);

    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        mCurrLocationMarker = map.addMarker(new MarkerOptions()
                .position(latLng)
                .title("My Location"));

        if (mLastLocation == null) {
            mLastLocation = new Location("Location");

        }
        mLastLocation.setLatitude(latLng.latitude);
        mLastLocation.setLongitude(latLng.longitude);
        lat=latLng.latitude;
        lon=latLng.longitude;
        Geocoder geocoder = new Geocoder(LocationActivity.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            String address = addresses.get(0).getAddressLine(0);
//            myaddress.setText(address);
            Log.e("getcityAddress: ", addresses.toString());
            Log.d("mylog", "Complete Address: " + addresses.toString());
            Log.d("mylog", "Address: " + address);//actual address
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else {
            requestPermissions();
        }
       return  false;

    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }
}
