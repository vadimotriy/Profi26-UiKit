package com.example.data;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {
    @POST("/login")
    Call<LoginResponse> login(@Body LoginRequest request);
}
