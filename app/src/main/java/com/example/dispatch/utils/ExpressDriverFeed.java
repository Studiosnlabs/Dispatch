package com.example.dispatch.utils;

import android.graphics.Bitmap;

public class ExpressDriverFeed {

    private Bitmap PackagePostImage;
    private int profileImage;
    private String UsernamePost;
    private String UserAddressPost;
    private String UserDestinationPost;

    public ExpressDriverFeed(Bitmap packagePostImage, int profileImage, String usernamePost, String userAddressPost, String userDestinationPost) {
        PackagePostImage = packagePostImage;
        this.profileImage = profileImage;
        UsernamePost = usernamePost;
        UserAddressPost = userAddressPost;
        UserDestinationPost = userDestinationPost;
    }

    public Bitmap getPackagePostImage() {
        return PackagePostImage;
    }

    public void setPackagePostImage(Bitmap packagePostImage) {
        PackagePostImage = packagePostImage;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public String getUsernamePost() {
        return UsernamePost;
    }

    public void setUsernamePost(String usernamePost) {
        UsernamePost = usernamePost;
    }

    public String getUserAddressPost() {
        return UserAddressPost;
    }

    public void setUserAddressPost(String userAddressPost) {
        UserAddressPost = userAddressPost;
    }

    public String getUserDestinationPost() {
        return UserDestinationPost;
    }

    public void setUserDestinationPost(String userDestinationPost) {
        UserDestinationPost = userDestinationPost;
    }
}
