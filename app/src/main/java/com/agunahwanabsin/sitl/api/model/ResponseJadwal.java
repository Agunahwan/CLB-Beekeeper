package com.agunahwanabsin.sitl.api.model;

import com.agunahwanabsin.sitl.model.Jadwal;

import java.util.List;

public class ResponseJadwal {
    List<Jadwal> ListJadwal;

    public List<Jadwal> getListJadwal() {
        return ListJadwal;
    }

    public void setListJadwal(List<Jadwal> listJadwal) {
        ListJadwal = listJadwal;
    }
}
