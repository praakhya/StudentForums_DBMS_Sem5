package com.projects.pes.forumbackend.utils;

import org.springframework.web.bind.annotation.RequestMethod;

//this file provides constants for different paths
public class Constants {
    public static final String BASE_PATH = "/api";

    public static class Paths {
        public static final String HELLO_PATH = BASE_PATH + "/hello";
        public static final String USER_PATH = BASE_PATH + "/user";
        public static final String DASHBOARD_PATH = BASE_PATH + "/dashboard";
        public static final String AUTH_PATH = BASE_PATH + "/auth";
        public static final String SECTION_PATH = BASE_PATH + "/section";
        public static final String SECTION_ID_PATH = "{id}";

        public static final String RESOURCE_PATH = BASE_PATH + "/resource";
        public static final String USER_GET_ONE_PATH = BASE_PATH + "/user/{userid}";
        public static final String FACULTY_PATH = BASE_PATH + "/faculty";
        public static final String STUDENT_PATH = BASE_PATH + "/student";

        public static final String FACULTY_GET_ONE_PATH = "{username}";
        public static final String IMAGE_UPLOAD_PATH = "/image/{username}";
        public static final String RESOURCE_UPLOAD_PATH = "/file";
        public static final String RESOURCE_GET_PATH = "/file/{id}/{filename}";
        public static final String RESOURCE_FOR_ID_PATH = "/{id}";
        public static final String RESOURCE_OF_USER_GET_PATH = "/user/{username}";

        public static final String FORUM_PATH = BASE_PATH + "/forum";
        public static final String UNSCRIBED_FORUMS_PATH = "/unsubscribed/{id}";

        public static final String GET_ALL_BRIEF_FORUMS_PATH = "/brief";
        public static final String FORUM_GET_ONE_PATH = "/{id}";
        public static final String POST_IN_A_FORUM_PATH = "/{forumId}/post";
        public static final String DELETE_POST_FROM_A_FORUM_PATH = "/{forumId}/post/{postId}";
        public static final String SUBSCRIBE_TO_A_FORUM_PATH = "/{forumId}/subscribe/{userId}";
        public static final String SUBSCRIBE_TO_A_FORUM_WITH_USERNAME_PATH = "/{forumId}/subscribe/{username}";
        public static final String GET_ALL_FORUM_USERS_PATH = "/forum/{forumId}/users";

        public static final String DUMMY_PROFILE_PICTURE = "/content/dummypp.png";
        public static final String SECTION_FROM_USERNAME = "/user/{username}";
        public static final String POSTS_COUNT = "/posts/count/{id}";
        public static final String NOTIFICATIONS = "/notifications/{id}";
    }
}
