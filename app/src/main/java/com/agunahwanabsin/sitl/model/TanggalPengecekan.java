package com.agunahwanabsin.sitl.model;

import com.google.gson.annotations.SerializedName;

public class TanggalPengecekan {
    @SerializedName("IdJadwalPengecekan")
    private int IdJadwalPengecekan;
    @SerializedName("IdBeekeper")
    private int IdBeekeper;
    @SerializedName("IdHasilPengecekan")
    private int IdHasilPengecekan;
    @SerializedName("KodeKotak")
    private String KodeKotak;
    @SerializedName("TanggalPengecekan")
    private String TanggalPengecekan;
    @SerializedName("Beekeper")
    private String Beekeper;
    @SerializedName("CreatedDate")
    private String CreatedDate;
    @SerializedName("CreatedBy")
    private String CreatedBy;
    @SerializedName("UpdatedDate")
    private String UpdatedDate;
    @SerializedName("UpdatedBy")
    private String UpdatedBy;

    public int getIdJadwalPengecekan() {
        return IdJadwalPengecekan;
    }

    public void setIdJadwalPengecekan(int idJadwalPengecekan) {
        IdJadwalPengecekan = idJadwalPengecekan;
    }

    public int getIdBeekeper() {
        return IdBeekeper;
    }

    public void setIdBeekeper(int idBeekeper) {
        IdBeekeper = idBeekeper;
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

    public String getTanggalPengecekan() {
        return TanggalPengecekan;
    }

    public void setTanggalPengecekan(String tanggalPengecekan) {
        TanggalPengecekan = tanggalPengecekan;
    }

    public String getBeekeper() {
        return Beekeper;
    }

    public void setBeekeper(String beekeper) {
        Beekeper = beekeper;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        UpdatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return UpdatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        UpdatedBy = updatedBy;
    }
}
