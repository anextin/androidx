package com.example.kafein.firebase5;

/**
 * Created by Kafein on 11/28/2018.
 */

public class User {

    private static String userName;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        User.userName = userName;
    }
}
