package com.jozistreet.user.api.friend;

import com.jozistreet.user.model.res.CommonRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Friend {
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("friend/all/")
    Call<CommonRes> getFriendAll(@Header("Authorization") String token,
                                @Query("offset") int offset,
                                @Query("page_size") int page_size);
}
