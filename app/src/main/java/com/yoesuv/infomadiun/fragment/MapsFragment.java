package com.yoesuv.infomadiun.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.yoesuv.infomadiun.R;
import com.yoesuv.infomadiun.data.Constant;
import com.yoesuv.infomadiun.models.MapsPin;
import com.yoesuv.infomadiun.utils.PinApiInterface;
import com.yoesuv.infomadiun.utils.RobotoRegularTextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnInfoWindowClickListener {

    private CoordinatorLayout cLayout;
    private Snackbar snackbar;

    private int mapType;
    private boolean moveCamera;
    private GoogleMap gMap;

    private Marker marker;

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
        if (MapsFragment.this.isVisible()) {
            loadMap(googleMap, mapType);
        }
    }

    private void loadMap(final GoogleMap googleMap, int type) {

        if (snackbar != null) {
            if (snackbar.isShown()) {
                snackbar.dismiss();
            }
        }

        if (type == 1) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        } else if (type == 2) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } else if (type == 3) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        } else if (type == 4) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        } else {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        if (moveCamera) {
            /* LOAD PIN */
            Retrofit retrofit = new Retrofit.Builder().baseUrl(getResources().getString(R.string.pins_feed))
                    .addConverterFactory(GsonConverterFactory.create()).build();

            PinApiInterface pinApiInterface = retrofit.create(PinApiInterface.class);
            Call<List<MapsPin>> cMaps = pinApiInterface.call();
            cMaps.enqueue(new Callback<List<MapsPin>>() {
                @Override
                public void onResponse(Call<List<MapsPin>> call, Response<List<MapsPin>> response) {
                    if (response.isSuccessful()) {
                        for (MapsPin pin : response.body()) {
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
                            marker = googleMap.addMarker(options);
                            TagObject tag = new TagObject(pin.getName(), pin.getImage());
                            marker.setTag(tag);

                        }
                    } else {
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
                public void onFailure(Call<List<MapsPin>> call, Throwable t) {
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
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-7.813882, 111.371713)));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(9));
        }

        googleMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation(googleMap);

        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);

        googleMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
        googleMap.setOnInfoWindowClickListener(this);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_map, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
        if (snackbar != null) {
            if (snackbar.isShown()) {
                snackbar.dismiss();
            }
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    private void enableMyLocation(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        }else{
            Log.d(Constant.TAG_DEBUG,"location is not granted");
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Log.d(Constant.TAG_DEBUG,"Info Window clicked");
        marker.hideInfoWindow();
    }

    private class TagObject{

        private String title, image;

        private TagObject(String title, String image){
            this.title = title;
            this.image = image;
        }
    }

    private class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{

        private View mContents;

        private CustomInfoWindowAdapter(){
            mContents = LayoutInflater.from(getContext()).inflate(R.layout.infowindow_content, null);
            mContents.setAlpha(0f);
        }

        @Override
        public View getInfoWindow(Marker marker) {
            render(marker, mContents);
            return mContents;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }

        private void render(Marker marker, View view){
            AppCompatTextView tvTitle = (AppCompatTextView) view.findViewById(R.id.textviewTitle);
            tvTitle.setText(marker.getTitle());
            tvTitle.setAlpha(0f);

            TagObject tag = (TagObject) marker.getTag();
            if(tag!=null){
                AlertDialog alertDialog;
                AlertDialog.Builder ab = new AlertDialog.Builder(getContext());

                View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_maps_infowindow, null);
                AppCompatImageView imgView = (AppCompatImageView) v.findViewById(R.id.imageviewDialogMaps);
                Picasso.with(getContext())
                        .load(tag.image)
                        .placeholder(R.drawable.img_default)
                        .error(R.drawable.img_default)
                        .into(imgView);
                imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                RobotoRegularTextView textName = (RobotoRegularTextView) v.findViewById(R.id.textviewNameDialogMaps);
                textName.setText(tag.title);

                ab.setView(v);
                alertDialog = ab.create();
                alertDialog.show();
            }
        }

    }
}
