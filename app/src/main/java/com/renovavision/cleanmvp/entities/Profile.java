package com.renovavision.cleanmvp.entities;

import com.twitter.sdk.android.core.models.User;

/**
 * Created by alexmprog on 21.12.2015.
 */
public class Profile {

    private final String handle;
    private final String profileImageUrl;

    public Profile(String handle, String profileImageUrl) {
        this.handle = handle;
        this.profileImageUrl = profileImageUrl;
    }

    public String getHandle() {
        return handle;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
