package com.projects.pes.forumbackend.utils;
//this file provides constants for different paths
public class Constants {
    public static final String BASE_PATH = "/api";

    public static class Paths {
        public static final String HELLO_PATH = BASE_PATH + "/hello";
        public static final String USER_PATH = BASE_PATH + "/users";
        public static final String USER_GET_ONE_PATH = BASE_PATH + "/users/{userid}";
        public static final String FACULTY_PATH = BASE_PATH + "/faculty";
        public static final String FACULTY_GET_ONE_PATH = "{username}";
        public static final String IMAGE_UPLOAD_PATH = "/image/{username}";
    }
}