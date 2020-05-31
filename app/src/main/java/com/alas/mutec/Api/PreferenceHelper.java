package com.alas.mutec.Api;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private String Adentro;
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
        return app_prefs.getBoolean(Adentro,false);
    }
}
