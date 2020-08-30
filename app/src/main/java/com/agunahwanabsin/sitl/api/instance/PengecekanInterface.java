package com.agunahwanabsin.sitl.api.instance;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PengecekanInterface {
    @Multipart
    @POST("pengecekan/save")
    Call<ResponseBody> save(
            @Part("IdJadwalPengecekan") int IdJadwalPengecekan,
            @Part("KodeHasilPengecekan") String KodeHasilPengecekan,
            @Part("CreatedBy") String CreatedBy
    );
}
