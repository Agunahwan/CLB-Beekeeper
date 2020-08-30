package com.agunahwanabsin.sitl.api.instance;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AbsensiInterface {
    @Multipart
    @POST("absensi/save")
    Call<ResponseBody> save(
            @Part MultipartBody.Part foto,
            @Part("IdBeekeper") int IdBeekeper,
            @Part("Longitude") double Longitude,
            @Part("Latitude") double Latitude,
            @Part("CreatedBy") String CreatedBy
    );

    @Headers("Content-Type: application/json")
    @POST("absensi/check")
    Call<Integer> checkAbsensiToday(@Body int id_beekeper);
}
