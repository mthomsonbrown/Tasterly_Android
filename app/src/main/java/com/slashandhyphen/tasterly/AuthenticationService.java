package com.slashandhyphen.tasterly;

import com.slashandhyphen.tasterly.Models.Beer;
import com.slashandhyphen.tasterly.Models.SessionResponse;
import com.slashandhyphen.tasterly.Models.User;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;


public interface AuthenticationService {

    @POST("/sign_in")
    void loginUser(@Body User user, Callback<SessionResponse> result);

    @POST("/users")
    void registerUser(@Body User user, Callback<SessionResponse> result);

    @POST("/beers")
    void addBeer(@Body Beer beer, Callback<SessionResponse> result);
}
