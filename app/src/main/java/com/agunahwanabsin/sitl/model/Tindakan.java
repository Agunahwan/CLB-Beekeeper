package com.agunahwanabsin.sitl.model;

import com.google.gson.annotations.SerializedName;

public class Tindakan {
    @SerializedName("IdTindakan")
    private int IdTindakan;
    @SerializedName("Tindakan")
    private String Tindakan;
    @SerializedName("CreatedDate")
    private String CreatedDate;
    @SerializedName("CreatedBy")
    private String CreatedBy;
    @SerializedName("UpdatedDate")
    private String UpdatedDate;
    @SerializedName("UpdatedBy")
    private String UpdatedBy;

    public int getIdTindakan() {
        return IdTindakan;
    }

    public void setIdTindakan(int idTindakan) {
        IdTindakan = idTindakan;
    }

    public String getTindakan() {
        return Tindakan;
    }

    public void setTindakan(String tindakan) {
        Tindakan = tindakan;
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

    @Override
    public String toString() {
        return this.getTindakan();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tindakan) {
            Tindakan c = (Tindakan) obj;
            if (c.getTindakan().equals(getTindakan())) return true;
        }

        return false;
    }
}
