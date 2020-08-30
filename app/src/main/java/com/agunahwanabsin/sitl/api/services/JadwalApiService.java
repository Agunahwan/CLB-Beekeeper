package com.agunahwanabsin.sitl.api.services;

import com.agunahwanabsin.sitl.api.helper.ApiClient;
import com.agunahwanabsin.sitl.api.instance.JadwalInterface;

public class JadwalApiService {
    public static JadwalInterface getListJadwal() {
        return ApiClient.getRetrofitInstance().create(JadwalInterface.class);
    }
}
