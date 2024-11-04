package com.jozistreet.user.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jozistreet.user.model.common.DeliverModel;

import java.util.ArrayList;

public class DeliverCartOrderRes {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("data")
    @Expose
    private ArrayList<DeliverModel> data;
    @SerializedName("message")
    @Expose
    private String message;
    public DeliverCartOrderRes(){}

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<DeliverModel> getData() {
        return data;
    }

    public void setData(ArrayList<DeliverModel> data) {
        this.data = data;
    }
}
