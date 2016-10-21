package arief.com.samplemvp;

import com.google.gson.Gson;

import java.io.IOException;

import arief.com.samplemvp.model.Result;
import arief.com.samplemvp.model.User;
import arief.com.samplemvp.network.Service;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright (C) PT. Sebangsa Bersama - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Originally written by Author Name sebangsa, 21/10/16
 */

public class LoginPresenter implements Mvp.LoginPresenter{
    private Mvp.LoginView mViewLogin;

    public LoginPresenter(Mvp.LoginView mViewLogin){
        this.mViewLogin = mViewLogin;
    }


    @Override
    public void doLogin(String username, String password) {
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
