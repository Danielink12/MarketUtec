package com.alas.mutec.Api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers({"Content-Type: application/json;charset=utf-8"})
    @FormUrlEncoded
    @POST("/Android/API/Login/")
    Call<User> login(@Field("username") String username, @Field("password") String password);


}
