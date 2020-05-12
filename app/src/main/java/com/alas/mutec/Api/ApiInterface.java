package com.alas.mutec.Api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @Headers({"Content-Type: application/json;charset=utf-8"})
    @POST("/Android/API/Login/")
    Call<String> login(@Body LoginModel loginModel);

    @POST("Android/API/Registrar")
    Call<String> registro(@Body RegistroModel registroModel);

    @GET("Android/API/Carreras")
    Call<List<CarrerasModel>> carreras();
}
