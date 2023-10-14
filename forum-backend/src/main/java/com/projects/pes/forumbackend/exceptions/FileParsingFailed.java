package com.projects.pes.forumbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
public class FileParsingFailed extends BaseException {
    public FileParsingFailed(String name) {
        super(HttpStatus.FAILED_DEPENDENCY.value(), String.format("File %s could not be parsed.", name));
        this.name = name;
    }
    public FileParsingFailed(String name, Throwable cause) {
        super(HttpStatus.FAILED_DEPENDENCY.value(), String.format("File %s could not be parsed.", name), cause);
        this.name = name;
    }

    public FileParsingFailed() {
    }
    private String name;
}