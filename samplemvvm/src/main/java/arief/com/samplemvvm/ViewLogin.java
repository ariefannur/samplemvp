package arief.com.samplemvvm;

import arief.com.samplemvvm.model.Result;
import arief.com.samplemvvm.model.User;

/**
 * Copyright (C) PT. Sebangsa Bersama - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Originally written by Author Name sebangsa, 21/10/16
 */

public interface ViewLogin {

    public void onError(Result result);
    public void onSuccess(User user);
}
