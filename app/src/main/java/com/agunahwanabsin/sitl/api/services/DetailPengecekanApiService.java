package com.agunahwanabsin.sitl.api.services;

import com.agunahwanabsin.sitl.api.helper.ApiClient;
import com.agunahwanabsin.sitl.api.instance.DetailPengecekanInterface;

public class DetailPengecekanApiService {
    public static DetailPengecekanInterface save() {
        return ApiClient.getRetrofitInstance().create(DetailPengecekanInterface.class);
    }

    public static DetailPengecekanInterface getDetailPengecekan() {
        return ApiClient.getRetrofitInstance().create(DetailPengecekanInterface.class);
    }
}
