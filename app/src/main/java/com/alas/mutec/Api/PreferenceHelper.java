package com.alas.mutec.Api;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private final String Adentro= "";
    private final String id="id";
    private final String token ="token";
    private final String nombre="nombre";
    private final String apellido="apellido";
    private final String carnet="carnet";
    private final String correo="correo";
    private final String telefono="telefono";
    private final String idcarrera="idcarrera";




    private SharedPreferences app_prefs;

    public PreferenceHelper(Context context){
        app_prefs = context.getSharedPreferences("shared",Context.MODE_PRIVATE);
    }

    public void logueado(Boolean logue){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(Adentro,logue);
        edit.apply();
    }
    public Boolean getIsLog(){
        return app_prefs.getBoolean(Adentro,false);
    }

    public void setToken(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(token, loginorout);
        edit.apply();
    }

    public String getToken(){
        return app_prefs.getString(token,"");
    }


    public void setID(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(id, loginorout);
        edit.apply();
    }

    public String getID(){
        return app_prefs.getString(id,"0");
    }


    public void setNombre(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(nombre, loginorout);
        edit.apply();
    }

    public String getNombre(){
        return app_prefs.getString(nombre,"");
    }

    public void setApellido(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(apellido, loginorout);
        edit.apply();
    }

    public String getApellido(){
        return app_prefs.getString(apellido,"");
    }

    public void setCorreo(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(correo, loginorout);
        edit.apply();
    }

    public String getCorreo(){
        return app_prefs.getString(correo,"");
    }

    public void setCarnet(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(carnet, loginorout);
        edit.apply();
    }

    public String getCarnet(){
        return app_prefs.getString(carnet,"");
    }

    public void setTelefono(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(telefono, loginorout);
        edit.apply();
    }

    public String getTelefono(){
        return app_prefs.getString(telefono,"");
    }

    public void setIdcarrera(int loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putInt(idcarrera, loginorout);
        edit.apply();
    }

    public int getIdcarrera(){
        return app_prefs.getInt(idcarrera,0);
    }



}
