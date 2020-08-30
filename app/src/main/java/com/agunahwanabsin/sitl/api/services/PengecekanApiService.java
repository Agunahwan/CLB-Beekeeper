package com.agunahwanabsin.sitl.api.services;

import com.agunahwanabsin.sitl.api.helper.ApiClient;
import com.agunahwanabsin.sitl.api.instance.PengecekanInterface;

public class PengecekanApiService {
    public static PengecekanInterface save() {
        return ApiClient.getRetrofitInstance().create(PengecekanInterface.class);
    }
}
