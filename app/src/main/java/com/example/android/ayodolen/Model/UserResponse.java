package com.example.android.ayodolen.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 18/11/2018.
 */

public class UserResponse {
    @SerializedName("status") private String status;
    @SerializedName("result") private User user;
//    @SerializedName("message") String message;

    public UserResponse(String status, User user) {
        this.status = status;
        this.user = user;
    }

//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
