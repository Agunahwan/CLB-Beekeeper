package com.agunahwanabsin.sitl.api.instance;

import com.agunahwanabsin.sitl.model.Blok;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BlokInterface {
    @GET("blok/list")
    Call<List<Blok>> getListBlok();
}
