package com.agunahwanabsin.sitl.api.model;

import com.google.gson.annotations.SerializedName;

public class RequestObject {
    @SerializedName("kode_kotak")
    private String KodeKotak;

    public RequestObject(String kodeKotak) {
        KodeKotak = kodeKotak;
    }

    public String getKodeKotak() {
        return KodeKotak;
    }

    public void setKodeKotak(String kodeKotak) {
        KodeKotak = kodeKotak;
    }
}
