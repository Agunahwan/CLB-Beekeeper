package com.agunahwanabsin.sitl.api.instance;

import com.agunahwanabsin.sitl.model.Tindakan;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TindakanInterface {
    @GET("tindakan/list")
    Call<List<Tindakan>> getListTindakan();
}
