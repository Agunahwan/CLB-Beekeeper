package com.agunahwanabsin.sitl.api.services;

import com.agunahwanabsin.sitl.api.helper.ApiClient;
import com.agunahwanabsin.sitl.api.instance.OwnerInterface;

public class OwnerApiService {
    public static OwnerInterface getListOwner() {
        return ApiClient.getRetrofitInstance().create(OwnerInterface.class);
    }
}
