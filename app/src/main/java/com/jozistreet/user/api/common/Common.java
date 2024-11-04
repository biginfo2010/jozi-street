package com.jozistreet.user.api.common;

import com.jozistreet.user.model.res.CommonRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface Common {
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("common/get-detail-from-ip/")
    Call<CommonRes> getAddress();
}
