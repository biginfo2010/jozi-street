package com.jozistreet.user.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jozistreet.user.model.common.ProductDetailModel;
import com.jozistreet.user.model.common.SingleProductModel;

import java.util.ArrayList;

public class ProductSingleRes {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("data")
    @Expose
    private ArrayList<SingleProductModel> data;
    @SerializedName("message")
    @Expose
    private String message;



    public ProductSingleRes(){
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<SingleProductModel> getData() {
        return data;
    }

    public void setData(ArrayList<SingleProductModel> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
