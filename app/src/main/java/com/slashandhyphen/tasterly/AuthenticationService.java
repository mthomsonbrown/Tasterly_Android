package com.slashandhyphen.tasterly;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;


public class AuthenticationService {

    String resString = "HUH?";

    public interface AuthenticationWebService {
        @GET("/api/v1/tasks.json")
        public void getAString(@Query("auth_token") String authToken, Callback<JSONObject> result);

        @GET("/users/{user}/repos")
        List<String> listRepos(@Path("user") String user);
    }

    public String getTasks(String authToken) {

        String mString = "NOT DATA";
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.1.100:3000")
                .build();
        AuthenticationWebService service = restAdapter.create(AuthenticationWebService.class);

        JSONObject result = new JSONObject();


        try {
            result.put("success", false);
            result.put("info", "Something went wrong. Retry!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // THis line causes fatal error...

        service.getAString(authToken, new Callback<JSONObject>() {
            @Override
            public void success(JSONObject result, Response response) {
                resString = "SUCCESS";
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                resString = "FAILURE";
            }

        });
        return resString;
    }
}
