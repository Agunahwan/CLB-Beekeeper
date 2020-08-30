package com.agunahwanabsin.sitl.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Beekeper {
    @SerializedName("IdBeekeper")
    private int IdBeekeper;
    @SerializedName("Beekeper")
    private String Beekeper;
    @SerializedName("Username")
    private String Username;
    @SerializedName("Password")
    private String Password;
    @SerializedName("CreatedDate")
    private String CreatedDate;
    @SerializedName("CreatedBy")
    private String CreatedBy;
    @SerializedName("UpdatedDate")
    private String UpdatedDate;
    @SerializedName("UpdatedBy")
    private String UpdatedBy;

    public int getIdBeekeper() {
        return IdBeekeper;
    }

    public void setIdBeekeper(int idBeekeper) {
        IdBeekeper = idBeekeper;
    }

    public String getBeekeper() {
        return Beekeper;
    }

    public void setBeekeper(String beekeper) {
        Beekeper = beekeper;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
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
