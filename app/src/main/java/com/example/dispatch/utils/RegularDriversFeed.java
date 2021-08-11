package com.example.dispatch.utils;

import android.graphics.Bitmap;

public class RegularDriversFeed {

    private Bitmap PackagePostImage;
    private Bitmap profileImage;
    private String UsernamePost;
    private String UserAddressPost;
    private String UserDestinationPost;
    private String MoreInfoPost;

    public RegularDriversFeed(Bitmap packagePostImage, Bitmap profileImage, String usernamePost, String userAddressPost, String userDestinationPost, String moreInfoPost) {
        PackagePostImage = packagePostImage;
        this.profileImage = profileImage;
        UsernamePost = usernamePost;
        UserAddressPost = userAddressPost;
        UserDestinationPost = userDestinationPost;
        MoreInfoPost = moreInfoPost;
    }

    public Bitmap getPackagePostImage() {
        return PackagePostImage;
    }

    public void setPackagePostImage(Bitmap packagePostImage) {
        PackagePostImage = packagePostImage;
    }

    public Bitmap getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Bitmap profileImage) {
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

    public String getMoreInfoPost() {
        return MoreInfoPost;
    }

    public void setMoreInfoPost(String moreInfoPost) {
        MoreInfoPost = moreInfoPost;
    }
}
