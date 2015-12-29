package com.renovavision.cleanmvp.repositories;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.User;

/**
 * Created by alexmprog on 21.12.2015.
 */
public interface UserRepository {
    void getUser(Callback<User> callback);
}
