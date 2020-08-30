package com.agunahwanabsin.sitl.api.instance;

import com.agunahwanabsin.sitl.api.model.RequestJadwal;
import com.agunahwanabsin.sitl.api.model.request.DetailPengecekanRequest;
import com.agunahwanabsin.sitl.model.DetailPengecekan;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DetailPengecekanInterface {
    @Multipart
    @POST("detail_pengecekan/save")
    Call<ResponseBody> save(
            @Part MultipartBody.Part foto,
            @Part("IdJadwalPengecekan") int IdJadwalPengecekan,
            @Part("KodeHasilPengecekan") String KodeHasilPengecekan,
            @Part("IdObject") int IdObject,
            @Part("IdStatusObject") int IdStatusObject,
            @Part("IdTindakan") int IdTindakan,
            @Part("Keterangan") String Keterangan,
            @Part("CreatedBy") String CreatedBy
    );

    @Headers("Content-Type: application/json")
    @POST("detail_pengecekan/get")
    Call<List<DetailPengecekan>> getDetailPengecekan(@Body DetailPengecekanRequest body);
}
