package com.agunahwanabsin.sitl.api.services;

import com.agunahwanabsin.sitl.api.helper.ApiClient;
import com.agunahwanabsin.sitl.api.instance.TanggalPengecekanInterface;

public class TanggalPengecekanApiService {
    public static TanggalPengecekanInterface getListTanggalPengecekan() {
        return ApiClient.getRetrofitInstance().create(TanggalPengecekanInterface.class);
    }
}
