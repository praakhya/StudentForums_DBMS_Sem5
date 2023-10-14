package com.projects.pes.forumbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserDoesntExist extends BaseException {
    public UserDoesntExist(String username) {
        super(HttpStatus.NOT_FOUND.value(), String.format("User id %s does not exist.", username));
        this.username = username;
    }

    public UserDoesntExist() {
    }
    private String username;
}