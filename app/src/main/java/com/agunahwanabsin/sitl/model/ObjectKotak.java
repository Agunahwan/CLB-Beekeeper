package com.agunahwanabsin.sitl.model;

import com.google.gson.annotations.SerializedName;

public class ObjectKotak {
    @SerializedName("IdRelKotakObject")
    private int IdRelKotakObject;
    @SerializedName("KodeKotak")
    private String KodeKotak;
    @SerializedName("IdObject")
    private int IdObject;
    @SerializedName("Object")
    private String Object;
    @SerializedName("CreatedDate")
    private String CreatedDate;
    @SerializedName("CreatedBy")
    private String CreatedBy;
    @SerializedName("IdDetailHasilPengecekan")
    private int IdDetailHasilPengecekan;

    public int getIdRelKotakObject() {
        return IdRelKotakObject;
    }

    public void setIdRelKotakObject(int idRelKotakObject) {
        IdRelKotakObject = idRelKotakObject;
    }

    public String getKodeKotak() {
        return KodeKotak;
    }

    public void setKodeKotak(String kodeKotak) {
        KodeKotak = kodeKotak;
    }

    public int getIdObject() {
        return IdObject;
    }

    public void setIdObject(int idObject) {
        IdObject = idObject;
    }

    public String getObject() {
        return Object;
    }

    public void setObject(String object) {
        Object = object;
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

    public int getIdDetailHasilPengecekan() {
        return IdDetailHasilPengecekan;
    }

    public void setIdDetailHasilPengecekan(int idDetailHasilPengecekan) {
        IdDetailHasilPengecekan = idDetailHasilPengecekan;
    }
}
