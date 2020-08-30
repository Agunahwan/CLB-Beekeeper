package com.agunahwanabsin.sitl.api.instance;

import com.agunahwanabsin.sitl.api.model.request.ObjectKotakRequest;
import com.agunahwanabsin.sitl.model.ObjectKotak;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ObjectKotakInterface {
    @Headers("Content-Type: application/json")
    @POST("object_pengecekan/list")
    Call<List<ObjectKotak>> getListObject(@Body ObjectKotakRequest body);
}
