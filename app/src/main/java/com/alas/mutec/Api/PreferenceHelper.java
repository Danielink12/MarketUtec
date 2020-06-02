package com.alas.mutec.Api;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private final String Adentro= "";
    private final String id="";
    private final String token ="";
    private SharedPreferences app_prefs;
    private Context context;

    public PreferenceHelper(Context context){
        app_prefs = context.getSharedPreferences("shared",Context.MODE_PRIVATE);
        this.context = context;
    }

    public void logueado(Boolean logue){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(Adentro,logue);
        edit.commit();
    }
    public Boolean getIsLog(){
        return app_prefs.getBoolean(Adentro,true);
    }

    public void setToken(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(token, loginorout);
        edit.commit();
    }

    public String getToken(){
        return app_prefs.getString(token,"");
    }


    public void setID(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(id, loginorout);
        edit.commit();
    }

    public String getID(){
        return app_prefs.getString(id,"");
    }

}
