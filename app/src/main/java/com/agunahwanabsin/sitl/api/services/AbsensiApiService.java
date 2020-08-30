package com.agunahwanabsin.sitl.api.services;

import com.agunahwanabsin.sitl.api.helper.ApiClient;
import com.agunahwanabsin.sitl.api.instance.AbsensiInterface;

public class AbsensiApiService {
    public static AbsensiInterface save() {
        return ApiClient.getRetrofitInstance().create(AbsensiInterface.class);
    }

    public static AbsensiInterface checkAbsensiToday() {
        return ApiClient.getRetrofitInstance().create(AbsensiInterface.class);
    }
}
