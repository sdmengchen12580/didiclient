package org.faqrobot.textrecyclerview.http.bean;

import android.os.Parcel;

/**
 * Created by yunwen on 2016/9/1.
 */

public class AccessToken {

    private String message;
    private int status;
    private String access_token;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }


    public AccessToken() {
    }

    protected AccessToken(Parcel in) {
        this.message = in.readString();
        this.status = in.readInt();
        this.access_token = in.readString();
    }
}
