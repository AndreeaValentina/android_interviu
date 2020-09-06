package com.example.andreeaproblem;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/public-api/users")
        // API's endpoints
    Call<ResponseBody> getUsersList();

// UserListResponse is POJO class to get the data from API, we use List<UserListResponse> in callback because the data in our API is starting from JSONArray
}
