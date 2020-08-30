package com.agunahwanabsin.sitl.api.services;

import com.agunahwanabsin.sitl.api.helper.ApiClient;
import com.agunahwanabsin.sitl.api.instance.TindakanInterface;

public class TindakanApiService {
    public static TindakanInterface getListTindakan() {
        return ApiClient.getRetrofitInstance().create(TindakanInterface.class);
    }
}
