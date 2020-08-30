package com.agunahwanabsin.sitl.api.model.response;

import com.google.gson.annotations.SerializedName;

public class ChangePasswordResponse {
    @SerializedName("IsSuccess")
    private boolean IsSuccess;
    @SerializedName("Message")
    private String Message;

    public boolean isSuccess() {
        return IsSuccess;
    }

    public void setSuccess(boolean success) {
        IsSuccess = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
