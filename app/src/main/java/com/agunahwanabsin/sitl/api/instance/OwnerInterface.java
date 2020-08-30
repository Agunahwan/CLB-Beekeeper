package com.agunahwanabsin.sitl.api.instance;

import com.agunahwanabsin.sitl.model.Owner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OwnerInterface {
    @GET("owner/list")
    Call<List<Owner>> getListOwner();
}
