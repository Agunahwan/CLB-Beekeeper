package com.agunahwanabsin.sitl.api.services;

import com.agunahwanabsin.sitl.api.helper.ApiClient;
import com.agunahwanabsin.sitl.api.instance.StatusObjectInterface;

public class StatusObjectApiService {
    public static StatusObjectInterface getListStatusObject() {
        return ApiClient.getRetrofitInstance().create(StatusObjectInterface.class);
    }
}
