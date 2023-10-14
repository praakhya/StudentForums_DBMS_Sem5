package com.projects.pes.forumbackend.pojo;

//it is the json object representation of the data that its endpoint returns

public record ProfileImage(String name, String url, String mimeType, byte[] image) {
}
