package com.agunahwanabsin.sitl.model;

import com.google.gson.annotations.SerializedName;

public class Jadwal {
    @SerializedName("IdJadwalPengecekan")
    private int IdJadwalPengecekan;
    @SerializedName("TanggalPengecekan")
    private String TanggalPengecekan;
    @SerializedName("KodeBlok")
    private String KodeBlok;
    @SerializedName("KodeKotak")
    private String KodeKotak;
    @SerializedName("Owner")
    private String Owner;
    @SerializedName("JumlahData")
    private int JumlahData;
    @SerializedName("JumlahPengecekan")
    private int JumlahPengecekan;
    @SerializedName("IdHasilPengecekan")
    private int IdHasilPengecekan;

    public int getIdJadwalPengecekan() {
        return IdJadwalPengecekan;
    }

    public void setIdJadwalPengecekan(int idJadwalPengecekan) {
        IdJadwalPengecekan = idJadwalPengecekan;
    }

    public String getTanggalPengecekan() {
        return TanggalPengecekan;
    }

    public void setTanggalPengecekan(String tanggalPengecekan) {
        TanggalPengecekan = tanggalPengecekan;
    }

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

    public int getJumlahData() {
        return JumlahData;
    }

    public void setJumlahData(int jumlahData) {
        JumlahData = jumlahData;
    }

    public int getJumlahPengecekan() {
        return JumlahPengecekan;
    }

    public void setJumlahPengecekan(int jumlahPengecekan) {
        JumlahPengecekan = jumlahPengecekan;
    }

    public int getIdHasilPengecekan() {
        return IdHasilPengecekan;
    }

    public void setIdHasilPengecekan(int idHasilPengecekan) {
        IdHasilPengecekan = idHasilPengecekan;
    }
}
