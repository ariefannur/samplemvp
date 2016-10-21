package arief.com.samplemvvm;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.io.IOException;

import arief.com.samplemvvm.model.Result;
import arief.com.samplemvvm.model.User;
import arief.com.samplemvvm.network.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.view.ViewAction1;
import rx.schedulers.Schedulers;

/**
 * Copyright (C) PT. Sebangsa Bersama - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Originally written by Author Name sebangsa, 20/10/16
 */

public class ViewModel extends BaseObservable {


    public ObservableField<String> message;
    public String email;
    public String password;
    private ViewLogin mViewLogin;


    public ViewModel(ViewLogin login){
        message = new ObservableField<>("");
        this.mViewLogin = login;
    }

    public void onLogin(View view){

        doLogin(getEmail(), getPassword());
        Log.d("AF", "ini "+email);
    }

    public void onTextChangedEmail(CharSequence s, int start, int before, int count) {
        email = s.toString();
    }

    public void onTextChangedPassword(CharSequence s, int start, int before, int count) {
        password = s.toString();
    }

    @Bindable
    public String getEmail() {
        return email;
    }


    @Bindable
    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void doLogin(String username, String password){

        Service.Api api = Service.create().create(Service.Api.class);
        api.login(username, password)

           .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e instanceof HttpException){
                            HttpException httpException = (HttpException) e;
                            try {
                                String error = httpException.response().errorBody().string();
                                  Result result = new Gson().fromJson(error, Result.class);
                                 mViewLogin.onError(result);
                            } catch (IOException e1) {


                            }

                        }
                    }

                    @Override
                    public void onNext(User user) {
                        mViewLogin.onSuccess(user);
                    }
                });

    }

}
