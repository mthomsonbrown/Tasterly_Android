package com.slashandhyphen.tasterly;

import com.slashandhyphen.tasterly.Models.SessionResponse;
import com.slashandhyphen.tasterly.Models.User;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;


public interface AuthenticationService {
    @POST("/api/v1/sessions.json")
    void loginUser(@Body User user, Callback<SessionResponse> result);

    @POST("/api/v1/registrations")
    void registerUser(@Body User user, Callback<SessionResponse> result);
}
