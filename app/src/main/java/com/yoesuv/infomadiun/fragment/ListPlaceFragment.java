package com.yoesuv.infomadiun.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.ListFragment;
import android.support.v4.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.yoesuv.infomadiun.MainPlaceDetailActivity;
import com.yoesuv.infomadiun.R;
import com.yoesuv.infomadiun.adapters.ListPlaceAdapter;
import com.yoesuv.infomadiun.models.ListPlace;
import com.yoesuv.infomadiun.utils.ListPlaceApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListPlaceFragment extends ListFragment{

    private ListPlaceAdapter adapter;
    private Retrofit retrofit;
    private Call<List<ListPlace>> callData;
    private CoordinatorLayout cLayout;
    private Snackbar snackbar;

    private int idLokasi,idLokasiBefore;

    public static ListPlaceFragment getInstance(){
        return new ListPlaceFragment();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        idLokasiBefore = 100;

        cLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinator_layout);
        setHasOptionsMenu(true);

        getListView().setDivider(new ColorDrawable(Color.TRANSPARENT));
        getListView().setPadding(0, 5, 0, 5);
        getListView().setVerticalScrollBarEnabled(false);
        getListView().setDrawSelectorOnTop(true);
        getListView().setClipToPadding(false);

        adapter = new ListPlaceAdapter(getActivity(),0);

        idLokasi = 0;
        loadTempat(idLokasi);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent i = new Intent(getActivity(), MainPlaceDetailActivity.class);
        i.putExtra(MainPlaceDetailActivity.EXTRA_PLACE, adapter.getItem(position));
        if(Build.VERSION.SDK_INT<=Build.VERSION_CODES.KITKAT) {
            startActivity(i);
        }else{
            /*ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    getActivity(), v.findViewById(R.id.imageView_thumbnail),
                    getActivity().getResources().getString(R.string.transition_gallery)
            );*/
            Pair<View, String> p1 = Pair.create(v.findViewById(R.id.imageView_thumbnail),
                    getActivity().getResources().getString(R.string.transition_gallery));
            Pair<View, String> p2 = Pair.create(v.findViewById(R.id.cardView_list_place),
                    getActivity().getResources().getString(R.string.transition_cardview));
            ActivityOptionsCompat optionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), p1, p2);
            startActivity(i, optionsCompat.toBundle());
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.semua){
            idLokasi=0;
            loadTempat(idLokasi);

        }else if(id==R.id.list_kab_madiun){
            idLokasi=1;
            loadTempat(idLokasi);

        }else if(id==R.id.list_kota_madiun){
            idLokasi=2;
            loadTempat(idLokasi);

        }else if(id==R.id.list_kab_magetan){
            idLokasi=3;
            loadTempat(idLokasi);

        }else if(id==R.id.list_kab_ngawi){
            idLokasi=4;
            loadTempat(idLokasi);

        }else if(id==R.id.list_kab_ponorogo){
            idLokasi=5;
            loadTempat(idLokasi);

        }else if(id==R.id.list_kab_pacitan){
            idLokasi=6;
            loadTempat(idLokasi);
        }

        item.setChecked(true);

        return super.onOptionsItemSelected(item);
    }

    private void loadTempat(final int id){
        if(idLokasiBefore==id){
            return;
        }

        setListShown(false);
        adapter.clear();

        if(id==0){
            retrofit = new Retrofit.Builder().baseUrl(getResources().getString(R.string.place_feed)).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }else if(id==1){
            retrofit = new Retrofit.Builder().baseUrl(getResources().getString(R.string.place_feed_kab_madiun)).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }else if(id==2){
            retrofit = new Retrofit.Builder().baseUrl(getResources().getString(R.string.place_feed_kota_madiun)).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }else if(id==3){
            retrofit = new Retrofit.Builder().baseUrl(getResources().getString(R.string.place_feed_kab_magetan)).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }else if(id==4){
            retrofit = new Retrofit.Builder().baseUrl(getResources().getString(R.string.place_feed_kab_ngawi)).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }else if(id==5){
            retrofit = new Retrofit.Builder().baseUrl(getResources().getString(R.string.place_feed_kab_ponorogo)).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }else if(id==6){
            retrofit = new Retrofit.Builder().baseUrl(getResources().getString(R.string.place_feed_kab_pacitan)).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }

        ListPlaceApiInterface listPlaceApiInterface = retrofit.create(ListPlaceApiInterface.class);

        if(id==0){
            callData = listPlaceApiInterface.call();
        }else if(id==1){
            callData = listPlaceApiInterface.call_kab_madiun();
        }else if(id==2){
            callData = listPlaceApiInterface.call_kota_madiun();
        }else if(id==3){
            callData = listPlaceApiInterface.call_kab_magetan();
        }else if(id==4){
            callData = listPlaceApiInterface.call_kab_ngawi();
        }else if(id==5){
            callData = listPlaceApiInterface.call_kab_ponorogo();
        }else if(id==6){
            callData = listPlaceApiInterface.call_kab_pacitan();
        }

        callData.enqueue(new Callback<List<ListPlace>>() {
            @Override
            public void onResponse(Call<List<ListPlace>> call, Response<List<ListPlace>> response) {
                if(response.isSuccessful()){
                    for(ListPlace dt : response.body()){
                        adapter.add(dt);
                    }
                    adapter.notifyDataSetChanged();
                    setListAdapter(adapter);
                    if(ListPlaceFragment.this.isVisible()) {
                        setListShown(true);
                    }
                }else{
                    if(ListPlaceFragment.this.isVisible()) {
                        setListShown(true);
                        snackbar = Snackbar.make(cLayout, getResources().getString(R.string.connection_failed), Snackbar.LENGTH_INDEFINITE);
                        snackbar.setAction(getResources().getString(R.string.try_again), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                loadTempat(idLokasi);
                            }
                        }).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ListPlace>> call, Throwable t) {
                if(ListPlaceFragment.this.isVisible()){
                    setListShown(true);
                    snackbar = Snackbar.make(cLayout, getResources().getString(R.string.connection_failed), Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction(getResources().getString(R.string.try_again), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadTempat(idLokasi);
                        }
                    }).show();
                }
            }
        });
        idLokasiBefore = id;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(snackbar!=null) {
            if (snackbar.isShown()) {
                snackbar.dismiss();
            }
        }
    }
}
