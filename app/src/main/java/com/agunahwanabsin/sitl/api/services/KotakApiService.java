package com.agunahwanabsin.sitl.api.services;

import com.agunahwanabsin.sitl.api.helper.ApiClient;
import com.agunahwanabsin.sitl.api.instance.KotakInterface;

public class KotakApiService {
    public static KotakInterface save() {
        return ApiClient.getRetrofitInstance().create(KotakInterface.class);
    }

    public static KotakInterface getListKotak(){
        return ApiClient.getRetrofitInstance().create(KotakInterface.class);
    }
}
