package com.example.domain;

import com.example.data.RepositoryImpl;

public interface Repository {
    public void getName(String email, String password, RepositoryImpl.MyCallback<String> callback);

}
