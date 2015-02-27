package com.slashandhyphen.tasterly;

import com.slashandhyphen.tasterly.Models.SessionResponse;
import com.slashandhyphen.tasterly.Models.Trial;
import com.slashandhyphen.tasterly.Models.User;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;


public interface AuthenticationService {
    @GET("/api/v1/tasks.json")
    public void getAStringList(@Query("auth_token") String authToken, Callback<Trial> result);

    @POST("/api/v1/sessions.json")
    void loginUser(@Body User user, Callback<SessionResponse> result);

    @POST("/api/v1/registrations")
    void registerUser(@Body User user, Callback<SessionResponse> result);
}
