package arief.com.samplemvp;

import arief.com.samplemvp.model.Result;
import arief.com.samplemvp.model.User;

/**
 * Copyright (C) PT. Sebangsa Bersama - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Originally written by Author Name sebangsa, 21/10/16
 */

public interface Mvp {

    public interface LoginView {
        public void onError(Result result);
        public void onSuccess(User user);
    }

    public interface LoginPresenter{
        public void doLogin(String username, String password);
    }
}
