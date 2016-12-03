package com.yoesuv.infomadiun.utils;

import com.yoesuv.infomadiun.models.InfoGallery;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

public interface InfoGalleryApiInterface {

    @GET("Gallery_info.json")
    Call<List<InfoGallery>> call();
}
