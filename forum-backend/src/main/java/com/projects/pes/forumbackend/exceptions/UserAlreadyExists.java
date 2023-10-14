package com.projects.pes.forumbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExists extends BaseException {
    public UserAlreadyExists(String username) {
        super(HttpStatus.CONFLICT.value(), String.format("User id %s does not exist.", username));
        this.username = username;
    }

    public UserAlreadyExists() {
    }
    private String username;
}