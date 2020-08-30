package com.agunahwanabsin.sitl.model;

import com.google.gson.annotations.SerializedName;

public class Owner {
    @SerializedName("IdOwner")
    private int IdOwner;
    @SerializedName("Owner")
    private String Owner;
    @SerializedName("Username")
    private String Username;
    @SerializedName("Password")
    private String Password;
    @SerializedName("CreatedDate")
    private String CreatedDate;
    @SerializedName("CreatedBy")
    private String CreatedBy;

    public int getIdOwner() {
        return IdOwner;
    }

    public void setIdOwner(int idOwner) {
        IdOwner = idOwner;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
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

    @Override
    public String toString() {
        return this.getOwner();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Owner) {
            Owner c = (Owner) obj;
            if (c.getOwner().equals(getOwner()) && c.getIdOwner() == getIdOwner()) return true;
        }

        return false;
    }
}
