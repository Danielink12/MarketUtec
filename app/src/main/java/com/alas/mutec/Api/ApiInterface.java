package com.alas.mutec.Api;

import android.util.JsonReader;

import com.alas.mutec.Fragments.Perfil;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.ResponseBody;
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

    @POST("Android/API/UsersAction/AddPublication") //pendiente
    Call<ResponseBody> crearpub(@Body PubModel pubModel, @Body ImgPubModel imgPubModel);

    @GET("Android/Admin/Lista")  //pendiente
    Call<List<CPubModel>> getpub();

    @GET("Android/API/UsersAction/Perfil/{idusuario}")  //pendiente
    Call<PerfilModel> getPerfil(@Path("idusuario") String idusuario, @Header("Authorization") String authHeader);

  //  @Headers({"Authorization", "Bearer "+ token})
    //D/OkHttp: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IjI1LTA0NTctMjAxOCIsIm5iZiI6MTU5MTA4MTk0NiwiZXhwIjoxNTkxMDk2MzQ2LCJpYXQiOjE1OTEwODE5NDYsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6NDkyMjAiLCJhdWQiOiJodHRwOi8vbG9jYWxob3N0OjQ5MjIwIn0.b2yzUrL5bqVjH1dCc6pUtH-ragsUmiq-s3N0mod130Y id:10"
//    Authorization","value":"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IjI1LTI4NTctMjAxOCIsIm5iZiI6MTU4ODkxMDcxNiwiZXhwIjoxNTg4OTI1MTE2LCJpYXQiOjE1ODg5MTA3MTYsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6NDkyMjAiLCJhdWQiOiJodHRwOi8vbG9jYWxob3N0OjQ5MjIwIn0.7mR3YADaQv0nsdW-OJA4KrMDu-z8XdTpNJ5Q7FBepDE"
}
