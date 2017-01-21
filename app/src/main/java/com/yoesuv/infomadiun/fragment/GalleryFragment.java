package com.yoesuv.infomadiun.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.yoesuv.infomadiun.GalleryDetailActivity;
import com.yoesuv.infomadiun.R;
import com.yoesuv.infomadiun.adapters.InfoGalleryAdapter;
import com.yoesuv.infomadiun.models.InfoGallery;
import com.yoesuv.infomadiun.utils.InfoGalleryApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GalleryFragment extends Fragment implements AdapterView.OnItemClickListener{

    private GridView gView;
    private InfoGalleryAdapter gAdapter;
    private CoordinatorLayout cLayout;
    private Snackbar snackbar;

    public static GalleryFragment getInstance(){
        return new GalleryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery_grid, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinator_layout);

        gView = (GridView) view.findViewById(R.id.grid_thumbnail);
        gView.setOnItemClickListener(this);
        gView.setDrawSelectorOnTop(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gAdapter = new InfoGalleryAdapter(getActivity(), 0);
        gView.setAdapter(gAdapter);
        gView.setVerticalScrollBarEnabled(false);

        if(GalleryFragment.this.isVisible()) {
            loadGallery();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        InfoGallery ig = (InfoGallery) parent.getItemAtPosition(position);

        Intent i = new Intent(getActivity(), GalleryDetailActivity.class);
        i.putExtra(GalleryDetailActivity.EXTRA_IMAGE, ig.getImage());
        i.putExtra(GalleryDetailActivity.EXTRA_DESC, ig.getCaption());
        startActivity(i);
    }


    private void loadGallery(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(getResources().getString(R.string.gallery_feed)).
                addConverterFactory(GsonConverterFactory.create()).build();

        InfoGalleryApiInterface infoGalleryApiInterface = retrofit.create(InfoGalleryApiInterface.class);

        Call<List<InfoGallery>> cg = infoGalleryApiInterface.call();
        cg.enqueue(new Callback<List<InfoGallery>>() {
            @Override
            public void onResponse(Call<List<InfoGallery>> call, Response<List<InfoGallery>> response) {
                if(response.isSuccessful()){
                    for (InfoGallery ig : response.body()) {
                        gAdapter.add(ig);
                    }
                    gAdapter.notifyDataSetChanged();
                }else{
                    if (GalleryFragment.this.isVisible()) {
                        snackbar = Snackbar.make(cLayout, getResources().getString(R.string.connection_failed), Snackbar.LENGTH_INDEFINITE);
                        snackbar.setAction(getResources().getString(R.string.try_again), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                loadGallery();
                            }
                        }).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<InfoGallery>> call, Throwable t) {
                if (GalleryFragment.this.isVisible()) {
                    snackbar = Snackbar.make(cLayout, getResources().getString(R.string.no_inet_connection), Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction(getResources().getString(R.string.try_again), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadGallery();
                        }
                    }).show();
                }
            }
        });
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
