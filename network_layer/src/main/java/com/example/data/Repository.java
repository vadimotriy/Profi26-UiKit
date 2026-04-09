package com.example.data;

import static android.widget.Toast.LENGTH_SHORT;

import android.util.Log;
import android.widget.Toast;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    Retrofit builder;

    public interface MyCallback<T> {
        void onResult(T data);
    }

    public Repository() {
        builder = new Retrofit.Builder().baseUrl("https://web-production-2e91f.up.railway.app/")
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public void getName(String email, String password, MyCallback<String> callback) {
        LoginRequest request = new LoginRequest(email, password);

        builder.create(Api.class).login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body().message.equals("Succes")) {
                    callback.onResult(response.body().name);
                } else {
                    callback.onResult("bad");
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("LOG_TAG", "error");
                callback.onResult("error");
            }
        });
    }
}
