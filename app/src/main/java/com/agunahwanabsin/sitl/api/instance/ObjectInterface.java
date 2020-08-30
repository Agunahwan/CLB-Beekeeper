package com.agunahwanabsin.sitl.api.instance;

import com.agunahwanabsin.sitl.api.model.RequestObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ObjectInterface {
    @Headers("Content-Type: application/json")
    @POST("object/list")
    Call<ResponseBody> getListObject(@Body RequestObject body);
}
