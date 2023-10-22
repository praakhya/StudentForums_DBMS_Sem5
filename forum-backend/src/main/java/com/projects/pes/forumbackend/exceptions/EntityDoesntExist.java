package com.projects.pes.forumbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityDoesntExist extends BaseException {
    public EntityDoesntExist(String attribute, String type) {
        super(HttpStatus.NOT_FOUND.value(), String.format("Entity (attribute: %s, type: %s) does not exist.", attribute, type));
        this.attribute = attribute;
    }

    public EntityDoesntExist() {
    }
    private String attribute;
}