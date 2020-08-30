package com.agunahwanabsin.sitl.api.instance;

import com.agunahwanabsin.sitl.api.model.request.ChangePasswordRequest;
import com.agunahwanabsin.sitl.api.model.response.ChangePasswordResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BeekeperInterface {
    // Login
    @FormUrlEncoded
    @POST("beekeper/login")
    Call<ResponseBody> login(@Field("username") String Username,
                             @Field("password") String Password);

    // Change Password
    @Headers("Content-Type: application/json")
    @POST("beekeeper/change_password")
    Call<ChangePasswordResponse> changePassword(@Body ChangePasswordRequest body);
}
