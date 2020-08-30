package com.agunahwanabsin.sitl.api.model.request;

import com.google.gson.annotations.SerializedName;

public class ObjectKotakRequest {
    @SerializedName("IdHasilPengecekan")
    private int IdHasilPengecekan;
    @SerializedName("KodeKotak")
    private String KodeKotak;

    public ObjectKotakRequest(int idHasilPengecekan, String kodeKotak) {
        IdHasilPengecekan = idHasilPengecekan;
        KodeKotak = kodeKotak;
    }

    public int getIdHasilPengecekan() {
        return IdHasilPengecekan;
    }

    public void setIdHasilPengecekan(int idHasilPengecekan) {
        IdHasilPengecekan = idHasilPengecekan;
    }

    public String getKodeKotak() {
        return KodeKotak;
    }

    public void setKodeKotak(String kodeKotak) {
        KodeKotak = kodeKotak;
    }
}
