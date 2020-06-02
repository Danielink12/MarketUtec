package com.alas.mutec.Api;

import android.util.JsonReader;

import com.alas.mutec.Fragments.Perfil;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @Headers({"Content-Type: application/json;charset=utf-8"})
    @POST("Android/API/Login")
    Call<String> login(@Body LoginModel loginModel);

    @POST("Android/API/Registrar")
    Call<String> registro(@Body RegistroModel registroModel);

    @GET("Android/API/Carreras")
    Call<List<CarrerasModel>> carreras();

    @GET("Android/API/Subcategorias")
    Call<List<SCatModel>> scat();

    @GET("UsersAction/Perfil/{id}")
    Call<PerfilModel> getPerfil(@Path("id") int idusuario, @Header("Authorization") String authHeader);

  //  @Headers({"Authorization", "Bearer "+ token})
}
