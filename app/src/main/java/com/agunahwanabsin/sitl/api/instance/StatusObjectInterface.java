package com.agunahwanabsin.sitl.api.instance;

import com.agunahwanabsin.sitl.model.StatusObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface StatusObjectInterface {
    @GET("status_object/list")
    Call<List<StatusObject>> getListStatusObject();
}
