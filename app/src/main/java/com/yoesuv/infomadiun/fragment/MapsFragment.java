package com.yoesuv.infomadiun.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yoesuv.infomadiun.R;
import com.yoesuv.infomadiun.models.MapsPin;
import com.yoesuv.infomadiun.utils.PinApiInterface;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener{

    private CoordinatorLayout cLayout;
    private Snackbar snackbar;

    private int mapType;
    private boolean moveCamera;
    private GoogleMap gMap;

    public static MapsFragment getInstance() {
        return new MapsFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinator_layout);
        setHasOptionsMenu(true);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        mapType = 1;
        moveCamera = true;
        if(MapsFragment.this.isVisible()) {
            loadMap(googleMap, mapType);
        }
    }

    private void loadMap(final GoogleMap googleMap, int type){

        if(snackbar!=null){
            if(snackbar.isShown()){
                snackbar.dismiss();
            }
        }

        if(type == 1){
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }else if(type == 2){
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }else if(type == 3){
            googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }else if(type ==4){
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }else{
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        if(moveCamera){
        /* LOAD PIN */
        Retrofit retrofit = new Retrofit.Builder().baseUrl(getResources().getString(R.string.pins_feed))
                .addConverterFactory(GsonConverterFactory.create()).build();

        PinApiInterface pinApiInterface = retrofit.create(PinApiInterface.class);
        Call<List<MapsPin>> cMaps = pinApiInterface.call();
            cMaps.enqueue(new Callback<List<MapsPin>>() {
                @Override
                public void onResponse(Response<List<MapsPin>> response, Retrofit retrofit) {
                    if (response.isSuccess()) {
                        for (MapsPin pin : response.body()) {
                            //Log.e("pin", pin.getName());
                            MarkerOptions options = new MarkerOptions().position(new LatLng(pin.getLatitude(), pin.getLongitude()));
                            options.title(pin.getName());
                            if (pin.getLokasi() == 6) {
                                options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
                            } else if (pin.getLokasi() == 5) {
                                options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                            } else if (pin.getLokasi() == 4) {
                                options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                            } else if (pin.getLokasi() == 3) {
                                options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                            } else if (pin.getLokasi() == 2) {
                                options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                            }
                            googleMap.addMarker(options);

                        }
                    } else {
                        //Log.e("gagal", "Koneksi Anda Bermasalah");
                        if (MapsFragment.this.isVisible()) {
                            snackbar = Snackbar.make(cLayout, getResources().getString(R.string.connection_failed), Snackbar.LENGTH_INDEFINITE);
                            snackbar.setAction(getResources().getString(R.string.try_again), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    loadMap(googleMap, mapType);
                                }
                            });
                            snackbar.show();
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    //Log.e("failure", "Failure to Connect "+t.getMessage());
                    if (MapsFragment.this.isVisible()) {
                        snackbar = Snackbar.make(cLayout, getResources().getString(R.string.no_inet_connection), Snackbar.LENGTH_INDEFINITE);
                        snackbar.setAction(getResources().getString(R.string.try_again), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                loadMap(googleMap, mapType);
                            }
                        });
                        snackbar.show();
                    }
                }
        });

        //if(moveCamera){
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-7.813882, 111.371713)));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(9));
        }

        googleMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();

        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_map, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.map_normal:
                item.setChecked(true);
                mapType = 1;
                moveCamera = false;
                loadMap(gMap, mapType);
                return true;

            case R.id.map_satellite:
                item.setChecked(true);
                mapType = 2;
                moveCamera = false;
                loadMap(gMap, mapType);
                return true;

            case R.id.map_terrain:
                item.setChecked(true);
                mapType = 3;
                moveCamera = false;
                loadMap(gMap, mapType);
                return true;

            case R.id.map_hybrid:
                item.setChecked(true);
                mapType = 4;
                moveCamera = false;
                loadMap(gMap, mapType);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Log.e("Snackbar", "Hide Maps Snackbar");
        if(snackbar!=null) {
            if (snackbar.isShown()) {
                snackbar.dismiss();
            }
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    private void enableMyLocation() {
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            //Log.e("permission","ACCESS LOCATION REQUIRED");
            AlertDialog.Builder ab = new AlertDialog.Builder(getActivity());
            ab.setMessage(getResources().getString(R.string.location_permission_denied));
            ab.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create();
        }else if(gMap!=null) {
            gMap.setMyLocationEnabled(true);
        }
    }
}
