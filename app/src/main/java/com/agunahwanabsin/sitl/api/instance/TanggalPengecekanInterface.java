package com.agunahwanabsin.sitl.api.instance;

import com.agunahwanabsin.sitl.api.model.request.TanggalPengecekanRequest;
import com.agunahwanabsin.sitl.model.TanggalPengecekan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TanggalPengecekanInterface {
    @Headers("Content-Type: application/json")
    @POST("beekeeper/jadwal/list")
    Call<List<TanggalPengecekan>> getListTanggalPengecekan(@Body TanggalPengecekanRequest body);
}
