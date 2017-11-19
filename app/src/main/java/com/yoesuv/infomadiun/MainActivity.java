package com.yoesuv.infomadiun;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;

import com.yoesuv.infomadiun.data.Constant;
import com.yoesuv.infomadiun.fragment.AboutFragment;
import com.yoesuv.infomadiun.fragment.GalleryFragment;
import com.yoesuv.infomadiun.fragment.HomeFragment;
import com.yoesuv.infomadiun.fragment.ListPlaceFragment;
import com.yoesuv.infomadiun.fragment.MapsFragment;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    private AccountHeader header;
    private Drawer result;
    private TextView tvTitle;
    private Toolbar toolbar;

    private String currentTitle;
    private int currentIdentifier;

    private StartAppAd ads = new StartAppAd(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        StartAppSDK.init(this, "200252166", true);
        StartAppAd.disableSplash();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvTitle = (TextView) toolbar.findViewById(R.id.textView_title);
        Typeface tf = Typeface.createFromAsset(getResources().getAssets(),"pacifico.ttf");
        tvTitle.setTypeface(tf);

        requestLocation();

        header = new AccountHeaderBuilder().withActivity(this).withHeaderBackground(R.drawable.drawer_header2)
                .withSavedInstance(savedInstanceState).build();

        result = new DrawerBuilder().withActivity(this).withToolbar(toolbar).withAccountHeader(header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.home).withIcon(R.drawable.home_96_drawer).withSelectedIcon(R.drawable.home_96).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.place_list).withIcon(R.drawable.tails_96_drawer).withSelectedIcon(R.drawable.tails_96).withIdentifier(2),
                        new PrimaryDrawerItem().withName(R.string.gallery).withIcon(R.drawable.gallery_96_drawer).withSelectedIcon(R.drawable.gallery_96).withIdentifier(3),
                        new PrimaryDrawerItem().withName(R.string.maps).withIcon(R.drawable.map_marker_96_drawer).withSelectedIcon(R.drawable.map_marker_96).withIdentifier(4),
                        new PrimaryDrawerItem().withName(R.string.about).withIcon(R.drawable.about_96_drawer).withSelectedIcon(R.drawable.about_96).withIdentifier(5)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {

                            if (currentIdentifier != drawerItem.getIdentifier()) {

                                if (drawerItem.getIdentifier() == 1) {
                                    currentTitle = getResources().getString(R.string.home);
                                    currentIdentifier = 1;
                                    HomeFragment home = new HomeFragment();
                                    home.setDrawer(result);
                                    changeFragment(home);


                                } else if (drawerItem.getIdentifier() == 2) {
                                    currentTitle = getResources().getString(R.string.place_list);
                                    currentIdentifier = 2;
                                    changeFragment(ListPlaceFragment.getInstance());

                                } else if (drawerItem.getIdentifier() == 3) {
                                    currentTitle = getResources().getString(R.string.gallery);
                                    currentIdentifier = 3;
                                    changeFragment(GalleryFragment.getInstance());

                                } else if (drawerItem.getIdentifier() == 4) {
                                    currentTitle = getResources().getString(R.string.maps);
                                    currentIdentifier = 4;
                                    changeFragment(MapsFragment.getInstance());

                                } else if (drawerItem.getIdentifier() == 5) {
                                    currentTitle = getResources().getString(R.string.about);
                                    currentIdentifier = 5;
                                    changeFragment(AboutFragment.getInstance());

                                } else {
                                    currentTitle = getResources().getString(R.string.home);
                                }

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        /* set toolbar title */
                                        if (getSupportActionBar() != null) {
                                            tvTitle.setText(currentTitle);
                                        }

                                        if(currentTitle.equalsIgnoreCase(getResources().getString(R.string.about))){
                                            if (getSupportActionBar() != null) {
                                                getSupportActionBar().setElevation(0);
                                            }
                                        }else{
                                            if (getSupportActionBar() != null) {
                                                getSupportActionBar().setElevation(5);
                                            }
                                        }
                                    }
                                }, getResources().getInteger(R.integer.handler_duration));
                            }
                        }
                        return false;
                    }
                }).withSavedInstance(savedInstanceState).build();

        currentTitle = this.getResources().getString(R.string.app_name);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setElevation(5);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        HomeFragment h = new HomeFragment();
        h.setDrawer(result);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, h).commit();
        currentIdentifier = 1;
        tvTitle.setText(currentTitle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ads.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ads.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(result.isDrawerOpen()){
            result.closeDrawer();
        }
        ads.onBackPressed();
    }

    private void changeFragment(final Fragment fragment){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
                fTransaction.setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit);
                fTransaction.replace(R.id.container, fragment).commit();
            }
        }, getResources().getInteger(R.integer.handler_duration));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("log","MainActivity # request code "+requestCode);
        switch (requestCode){
            case Constant.REQUEST_LOCATION_CODE:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Log.d(Constant.TAG_DEBUG,"location permission granted");
                    requestStorage();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * request location permission
     * @return boolean
     */
    private boolean requestLocation(){
        Log.d(Constant.TAG_DEBUG,"request location permission");
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)){
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, Constant.REQUEST_LOCATION_CODE);
                    return false;
                }else{
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, Constant.REQUEST_LOCATION_CODE);
                    return false;
                }
            }else{
                return true;
            }
        }else{
            return true;
        }
    }

    /**
     * request read & write external storage permission
     * @return boolean
     */
    private boolean requestStorage(){
        Log.d(Constant.TAG_DEBUG,"request storage permission");
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constant.REQUEST_STORAGE_CODE);
                    return false;
                }else{
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constant.REQUEST_STORAGE_CODE);
                    return false;
                }
            }else{
                return true;
            }
        }else{
            return true;
        }
    }
}
