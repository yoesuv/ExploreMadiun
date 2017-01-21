package com.yoesuv.infomadiun.utils;

import com.yoesuv.infomadiun.models.ListPlace;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ListPlaceApiInterface {

    @GET("List_place.json")
    Call<List<ListPlace>> call();

    @GET("List_place_kab_madiun.json")
    Call<List<ListPlace>> call_kab_madiun();

    @GET("List_place_kota_madiun.json")
    Call<List<ListPlace>> call_kota_madiun();

    @GET("List_place_kab_magetan.json")
    Call<List<ListPlace>> call_kab_magetan();

    @GET("List_place_kab_ngawi.json")
    Call<List<ListPlace>> call_kab_ngawi();

    @GET("List_place_kab_ponorogo.json")
    Call<List<ListPlace>> call_kab_ponorogo();

    @GET("List_place_kab_pacitan.json")
    Call<List<ListPlace>> call_kab_pacitan();
}
