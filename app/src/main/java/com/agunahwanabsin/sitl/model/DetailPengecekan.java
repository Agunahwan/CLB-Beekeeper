package com.agunahwanabsin.sitl.model;

import com.google.gson.annotations.SerializedName;

public class DetailPengecekan {
    @SerializedName("IdDetailHasilPengecekan")
    private int IdDetailHasilPengecekan;
    @SerializedName("IdHasilPengecekan")
    private int IdHasilPengecekan;
    @SerializedName("IdObject")
    private int IdObject;
    @SerializedName("IdStatusObject")
    private int IdStatusObject;
    @SerializedName("IdTindakan")
    private int IdTindakan;
    @SerializedName("Keterangan")
    private String Keterangan;
    @SerializedName("Image")
    private String Image;
    @SerializedName("CreatedDate")
    private String CreatedDate;
    @SerializedName("CreatedBy")
    private String CreatedBy;
    @SerializedName("StatusObject")
    private String StatusObject;
    @SerializedName("Tindakan")
    private String Tindakan;

    public int getIdDetailHasilPengecekan() {
        return IdDetailHasilPengecekan;
    }

    public void setIdDetailHasilPengecekan(int idDetailHasilPengecekan) {
        IdDetailHasilPengecekan = idDetailHasilPengecekan;
    }

    public int getIdHasilPengecekan() {
        return IdHasilPengecekan;
    }

    public void setIdHasilPengecekan(int idHasilPengecekan) {
        IdHasilPengecekan = idHasilPengecekan;
    }

    public int getIdObject() {
        return IdObject;
    }

    public void setIdObject(int idObject) {
        IdObject = idObject;
    }

    public int getIdStatusObject() {
        return IdStatusObject;
    }

    public void setIdStatusObject(int idStatusObject) {
        IdStatusObject = idStatusObject;
    }

    public int getIdTindakan() {
        return IdTindakan;
    }

    public void setIdTindakan(int idTindakan) {
        IdTindakan = idTindakan;
    }

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String keterangan) {
        Keterangan = keterangan;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
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

    public String getStatusObject() {
        return StatusObject;
    }

    public void setStatusObject(String statusObject) {
        StatusObject = statusObject;
    }

    public String getTindakan() {
        return Tindakan;
    }

    public void setTindakan(String tindakan) {
        Tindakan = tindakan;
    }
}
