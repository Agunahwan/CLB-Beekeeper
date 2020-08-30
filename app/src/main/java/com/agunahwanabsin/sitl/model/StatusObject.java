package com.agunahwanabsin.sitl.model;

import com.google.gson.annotations.SerializedName;

public class StatusObject {
    @SerializedName("IdStatusObject")
    private int IdStatusObject;
    @SerializedName("StatusObject")
    private String StatusObject;
    @SerializedName("CreatedDate")
    private String CreatedDate;
    @SerializedName("CreatedBy")
    private String CreatedBy;
    @SerializedName("UpdatedDate")
    private String UpdatedDate;
    @SerializedName("UpdatedBy")
    private String UpdatedBy;

    public int getIdStatusObject() {
        return IdStatusObject;
    }

    public void setIdStatusObject(int idStatusObject) {
        IdStatusObject = idStatusObject;
    }

    public String getStatusObject() {
        return StatusObject;
    }

    public void setStatusObject(String statusObject) {
        StatusObject = statusObject;
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
        return this.getStatusObject();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StatusObject) {
            StatusObject c = (StatusObject) obj;
            if (c.getStatusObject().equals(getStatusObject())) return true;
        }

        return false;
    }
}
