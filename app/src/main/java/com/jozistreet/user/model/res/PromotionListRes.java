package com.jozistreet.user.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jozistreet.user.model.common.PromotionModel;
import com.jozistreet.user.model.common.PromotionOneModel;

import java.util.ArrayList;

public class PromotionListRes {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("data")
    @Expose
    private ArrayList<PromotionModel> data;
    @SerializedName("message")
    @Expose
    private String message;

    public PromotionListRes(){
        data = new ArrayList<>();
        data.clear();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<PromotionModel> getData() {
        return data;
    }

    public void setData(ArrayList<PromotionModel> data) {
        this.data = data;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
