package com.agunahwanabsin.sitl.api.model;

import com.google.gson.annotations.SerializedName;

public class RequestJadwal {
    @SerializedName("id_beekeper")
    private int IdBeekeper;

    public RequestJadwal(int idBeekeper) {
        IdBeekeper = idBeekeper;
    }

    public int isIdBeekeper() {
        return IdBeekeper;
    }

    public void setIdBeekeper(int idBeekeper) {
        IdBeekeper = idBeekeper;
    }
}
