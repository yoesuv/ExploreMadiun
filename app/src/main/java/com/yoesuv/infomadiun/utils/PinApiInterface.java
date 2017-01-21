package com.yoesuv.infomadiun.utils;

import com.yoesuv.infomadiun.models.MapsPin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PinApiInterface {

    @GET("maps_info.json")
    Call<List<MapsPin>> call();
}
