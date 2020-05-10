package com.alas.mutec.Api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    String isLogin;

    public String getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(String username) {
        this.isLogin = username;
    }

}
