package com.agunahwanabsin.sitl.api.instance;

import com.agunahwanabsin.sitl.api.model.RequestJadwal;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JadwalInterface {
    @Headers("Content-Type: application/json")
    @POST("jadwal/list")
    Call<ResponseBody> getListJadwal(@Body RequestJadwal body);
}
