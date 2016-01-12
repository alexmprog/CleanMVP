package com.renovavision.cleanmvp.interactors;

import com.renovavision.cleanmvp.model.Profile;
import com.twitter.sdk.android.core.Callback;

/**
 * Created by alexmprog on 21.12.2015.
 */
public interface ProfileInteractor {
    void getUserProfile(Callback<Profile> callback);
}
