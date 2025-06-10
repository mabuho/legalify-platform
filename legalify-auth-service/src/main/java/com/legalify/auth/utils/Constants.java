package com.legalify.auth.utils;

public class Constants {

    public static final String TOKEN_URI = "https://%s/oauth/token";
    public static final String AUTH_BASE_API_URI = "https://%s/api/v2";
    public static final String AUTH_API_USERS_URI = AUTH_BASE_API_URI + "/users";
    public static final String AUTH_API_USER_ROLES_URI = AUTH_API_USERS_URI + "/%s/roles";
    public static final String AUTH_API_ROLES_URI = AUTH_BASE_API_URI + "/roles";
    public static final String LOGIN_SCOPE = "openid profile email";
    public static final String UP_CONNECTION = "Username-Password-Authentication";

}
