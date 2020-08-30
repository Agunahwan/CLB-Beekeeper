package com.agunahwanabsin.sitl.api.model.request;

import com.google.gson.annotations.SerializedName;

public class Kotak {
    @SerializedName("IdKotak")
    private int IdKotak;
    @SerializedName("KodeKotak")
    private String KodeKotak;
    @SerializedName("KodeBlok")
    private String KodeBlok;
    @SerializedName("IdOwner")
    private int IdOwner;
    @SerializedName("EstimasiTanggalPanen")
    private String EstimasiTanggalPanen;
    @SerializedName("Deskripsi")
    private String Deskripsi;
    @SerializedName("CreatedBy")
    private String CreatedBy;

    public int getIdKotak() {
        return IdKotak;
    }

    public void setIdKotak(int idKotak) {
        IdKotak = idKotak;
    }

    public String getKodeKotak() {
        return KodeKotak;
    }

    public void setKodeKotak(String kodeKotak) {
        KodeKotak = kodeKotak;
    }

    public String getKodeBlok() {
        return KodeBlok;
    }

    public void setKodeBlok(String kodeBlok) {
        KodeBlok = kodeBlok;
    }

    public int getIdOwner() {
        return IdOwner;
    }

    public void setIdOwner(int idOwner) {
        IdOwner = idOwner;
    }

    public String getEstimasiTanggalPanen() {
        return EstimasiTanggalPanen;
    }

    public void setEstimasiTanggalPanen(String estimasiTanggalPanen) {
        EstimasiTanggalPanen = estimasiTanggalPanen;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }
}
