package com.slashandhyphen.tasterly;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


public interface AuthenticationService {
        @GET("/api/v1/tasks")
        public void getAStringList(@Query("auth_token") String authToken, Callback<Trial> result);

}
