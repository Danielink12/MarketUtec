package com.alas.mutec.Api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("Authorization")
    private String Authorization;

    @SerializedName("message")
    private String msg;

    public User(String authorization, String msg) {
        Authorization = authorization;
        this.msg = msg;
    }

    public String getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(String authorization) {
        Authorization = authorization;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
