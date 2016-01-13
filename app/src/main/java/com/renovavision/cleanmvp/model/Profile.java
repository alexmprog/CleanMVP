package com.renovavision.cleanmvp.model;

/**
 * Created by alexmprog on 21.12.2015.
 */
public class Profile {

    private String nName;
    private String nProfileImageUrl;

    public Profile(String name, String profileImageUrl) {
        this.nName = name;
        this.nProfileImageUrl = profileImageUrl;
    }

    public String getName() {
        return nName;
    }

    public String getProfileImageUrl() {
        return nProfileImageUrl;
    }
}
