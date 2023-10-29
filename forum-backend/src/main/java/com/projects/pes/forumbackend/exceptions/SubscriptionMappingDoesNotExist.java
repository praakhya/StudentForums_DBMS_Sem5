package com.projects.pes.forumbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
public class SubscriptionMappingDoesNotExist extends BaseException {
    public SubscriptionMappingDoesNotExist(String name1, String name2) {
        super(HttpStatus.FAILED_DEPENDENCY.value(), String.format("%s is not subscribed to %s", name1, name2));
    }

    public SubscriptionMappingDoesNotExist() {
    }
}