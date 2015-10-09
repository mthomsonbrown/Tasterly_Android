package com.slashandhyphen.tasterly;

import com.slashandhyphen.tasterly.Models.SessionResponse;
import com.slashandhyphen.tasterly.Models.User;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;


public interface AuthenticationService {

    @POST("/sign_in")
    void loginUser(@Body User user, Callback<SessionResponse> result);

    @POST("/users")
    void registerUser(@Body User user, Callback<SessionResponse> result);
}
