package com.projects.pes.forumbackend.exceptions;

public class BaseException extends RuntimeException {

    private int errorCode;

    public BaseException() {
        super("");
    }
    public BaseException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    public BaseException(Throwable cause) {
        super(cause);
    }
    public BaseException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}