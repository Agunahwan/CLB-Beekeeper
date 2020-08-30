package com.agunahwanabsin.sitl.api.services;

import com.agunahwanabsin.sitl.api.helper.ApiClient;
import com.agunahwanabsin.sitl.api.instance.ObjectInterface;

public class ObjectApiService {
    public static ObjectInterface getListObject() {
        return ApiClient.getRetrofitInstance().create(ObjectInterface.class);
    }
}
