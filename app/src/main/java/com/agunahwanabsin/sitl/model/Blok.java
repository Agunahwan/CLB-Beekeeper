package com.agunahwanabsin.sitl.model;

import com.google.gson.annotations.SerializedName;

public class Blok {
    @SerializedName("KodeBlok")
    private String KodeBlok;
    @SerializedName("CreatedDate")
    private String CreatedDate;
    @SerializedName("CreatedBy")
    private String CreatedBy;

    public String getKodeBlok() {
        return KodeBlok;
    }

    public void setKodeBlok(String kodeBlok) {
        KodeBlok = kodeBlok;
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

    @Override
    public String toString() {
        return this.getKodeBlok();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Blok) {
            Blok c = (Blok) obj;
            if (c.getKodeBlok().equals(getKodeBlok())) return true;
        }

        return false;
    }
}
