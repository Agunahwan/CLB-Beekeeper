package com.agunahwanabsin.sitl.api.model.request;

import com.google.gson.annotations.SerializedName;

public class TanggalPengecekanRequest {
    @SerializedName("KodeKotak")
    private String KodeKotak;

    public TanggalPengecekanRequest(String kodeKotak) {
        KodeKotak = kodeKotak;
    }

    public String getKodeKotak() {
        return KodeKotak;
    }

    public void setKodeKotak(String kodeKotak) {
        KodeKotak = kodeKotak;
    }
}
