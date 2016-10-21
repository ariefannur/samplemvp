package arief.com.samplemvvm;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;

import arief.com.samplemvvm.model.Result;

import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Copyright (C) PT. Sebangsa Bersama - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Originally written by Author Name sebangsa, 21/10/16
 */


public class LoginUnitTest {

    public String failed_username = "jhony";
    public String username = "cerminuser";
    public String failed_password = "123456";
    public String password = "cermin12345";


    @Mock
    private ViewLogin mViewLogin;
    @Mock
    private ViewModel mViewModel;


    @Captor
    private ArgumentCaptor<ViewModel> captorLogin;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        mViewModel = new ViewModel(mViewLogin);

    }

    @Test
    public void testLoginFailed(){

        mViewModel.doLogin(failed_username, failed_password);
        Log.d("AF", "ini "+mViewLogin.toString());
        verify(mViewLogin).onError(isA(Result.class));

    }



}
