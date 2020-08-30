package com.agunahwanabsin.sitl.api.services;

import com.agunahwanabsin.sitl.api.helper.ApiClient;
import com.agunahwanabsin.sitl.api.instance.ObjectKotakInterface;

public class ObjectKotakApiService {
    public static ObjectKotakInterface getListObject(){
        return ApiClient.getRetrofitInstance().create(ObjectKotakInterface.class);
    }
}
