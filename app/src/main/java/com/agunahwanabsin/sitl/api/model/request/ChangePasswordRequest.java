package com.agunahwanabsin.sitl.api.model.request;

import com.google.gson.annotations.SerializedName;

public class ChangePasswordRequest {
    @SerializedName("IdBeekeper")
    private int IdBeekeper;
    @SerializedName("OldPassword")
    private String OldPassword;
    @SerializedName("NewPassword")
    private String NewPassword;
    @SerializedName("ConfirmPassword")
    private String ConfirmPassword;

    public ChangePasswordRequest(int idBeekeper, String oldPassword, String newPassword, String confirmPassword) {
        IdBeekeper = idBeekeper;
        OldPassword = oldPassword;
        NewPassword = newPassword;
        ConfirmPassword = confirmPassword;
    }

    public int getIdBeekeper() {
        return IdBeekeper;
    }

    public void setIdBeekeper(int idBeekeper) {
        IdBeekeper = idBeekeper;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String oldPassword) {
        OldPassword = oldPassword;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }
}
