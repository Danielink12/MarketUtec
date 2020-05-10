package com.alas.mutec.Api;

import com.google.gson.stream.JsonToken;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers({"Content-Type: application/json;charset=utf-8"})
    @POST("/Android/API/Login/")
    Call<String> login(@Body LoginModel loginModel);

    @POST("Android/API/Registrar")
    Call<String> registro(@Body RegistroModel registroModel);
}
