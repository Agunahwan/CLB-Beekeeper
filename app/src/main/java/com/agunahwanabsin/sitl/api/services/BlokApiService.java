package com.agunahwanabsin.sitl.api.services;

import com.agunahwanabsin.sitl.api.helper.ApiClient;
import com.agunahwanabsin.sitl.api.instance.BlokInterface;

public class BlokApiService {
    public static BlokInterface getListBlok() {
        return ApiClient.getRetrofitInstance().create(BlokInterface.class);
    }
}
