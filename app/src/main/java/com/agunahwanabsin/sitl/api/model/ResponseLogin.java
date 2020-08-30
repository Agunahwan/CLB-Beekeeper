package com.agunahwanabsin.sitl.api.model;

import com.agunahwanabsin.sitl.model.Beekeper;
import com.google.gson.annotations.SerializedName;

public class ResponseLogin {
    @SerializedName("IsSuccess")
    private boolean IsSuccess;
    @SerializedName("Message")
    private String Message;
    @SerializedName("Data")
    private Beekeper Data;

    public ResponseLogin() {
    }

    public ResponseLogin(boolean IsSuccess, String Message, Beekeper Data) {
        this.IsSuccess = IsSuccess;
        this.Message = Message;
        this.Data = Data;
    }

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

    public Beekeper getData() {
        return Data;
    }

    public void setData(Beekeper data) {
        Data = data;
    }
}
