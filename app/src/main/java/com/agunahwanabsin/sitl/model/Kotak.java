package com.agunahwanabsin.sitl.model;

import com.google.gson.annotations.SerializedName;

public class Kotak {
    @SerializedName("KodeBlok")
    private String KodeBlok;
    @SerializedName("KodeKotak")
    private String KodeKotak;
    @SerializedName("Owner")
    private String Owner;
    @SerializedName("JumlahObject")
    private int JumlahObject;
    @SerializedName("EstimasiTanggalPanen")
    private String EstimasiTanggalPanen;

    public String getKodeBlok() {
        return KodeBlok;
    }

    public void setKodeBlok(String kodeBlok) {
        KodeBlok = kodeBlok;
    }

    public String getKodeKotak() {
        return KodeKotak;
    }

    public void setKodeKotak(String kodeKotak) {
        KodeKotak = kodeKotak;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public int getJumlahObject() {
        return JumlahObject;
    }

    public void setJumlahObject(int jumlahObject) {
        JumlahObject = jumlahObject;
    }

    public String getEstimasiTanggalPanen() {
        return EstimasiTanggalPanen;
    }

    public void setEstimasiTanggalPanen(String estimasiTanggalPanen) {
        EstimasiTanggalPanen = estimasiTanggalPanen;
    }
}
