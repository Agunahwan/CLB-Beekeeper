package com.agunahwanabsin.sitl.api.model.request;

import com.google.gson.annotations.SerializedName;

public class DetailPengecekanRequest {
    @SerializedName("IdJadwalPengecekan")
    private int IdJadwalPengecekan;
    @SerializedName("IdObject")
    private int IdObject;

    public DetailPengecekanRequest(int idJadwalPengecekan, int idObject) {
        IdJadwalPengecekan = idJadwalPengecekan;
        IdObject = idObject;
    }

    public int getIdHasilPengecekan() {
        return IdJadwalPengecekan;
    }

    public void setIdHasilPengecekan(int idJadwalPengecekan) {
        IdJadwalPengecekan = idJadwalPengecekan;
    }

    public int getIdObject() {
        return IdObject;
    }

    public void setIdObject(int idObject) {
        IdObject = idObject;
    }
}
