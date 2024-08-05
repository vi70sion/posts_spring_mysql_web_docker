package com.example.spring_posts;

public class Constants {
    public final static String URL = "jdbc:mysql://host.docker.internal:3308/postsdb";
    public final static String USERNAME = "root";
    public final static String PASSWORD = "1234";

    private Constants() {
        throw new AssertionError("Cannot instantiate the Constants class");
    }

}
