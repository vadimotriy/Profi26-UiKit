package com.example.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.data.Repository;

public class GameVIewModel extends ViewModel {
    private Repository repository = new Repository();

    MutableLiveData<String> name = new MutableLiveData<>();

    public LiveData<String> getName(String email, String pswrd){
        repository.getName(email, pswrd, result -> {
            // Как только сервер ответит, LiveData обновится
            name.setValue(result);
        });
        return name;
    }
}
