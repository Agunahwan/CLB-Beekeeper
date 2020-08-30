package com.agunahwanabsin.sitl.api.instance;

import com.agunahwanabsin.sitl.model.Kotak;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface KotakInterface {
    @Multipart
    @POST("kotak/save")
    Call<ResponseBody> save(
            @Part("KodeKotak") String KodeKotak,
            @Part("KodeBlok") String KodeBlok,
            @Part("IdOwner") int IdOwner,
            @Part("EstimasiTanggalPanen") String EstimasiTanggalPanen,
            @Part("Deskripsi") String Deskripsi,
            @Part("CreatedBy") String CreatedBy
    );

    @Headers("Content-Type: application/json")
    @POST("kotak/list")
    Call<List<Kotak>> getListKotak();
}
