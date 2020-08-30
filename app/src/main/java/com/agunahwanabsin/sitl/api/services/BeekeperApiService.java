package com.agunahwanabsin.sitl.api.services;

import com.agunahwanabsin.sitl.api.helper.ApiClient;
import com.agunahwanabsin.sitl.api.instance.BeekeperInterface;

public class BeekeperApiService {
    public static BeekeperInterface login(){
        return ApiClient.getRetrofitInstance().create(BeekeperInterface.class);
    }

    public static BeekeperInterface changePassword(){
        return ApiClient.getRetrofitInstance().create(BeekeperInterface.class);
    }
}
