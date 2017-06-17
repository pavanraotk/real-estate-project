package com.realty.project.service.exception;

public class ServiceException extends Exception {

    private String errorId;

    public ServiceException(String errorId) {
        this.errorId = errorId;
    }

    public ServiceException(String message, String errorId) {
        super(message);
        this.errorId = errorId;
    }

    public ServiceException(String message, Throwable cause, String errorId) {
        super(message, cause);
        this.errorId = errorId;
    }

    public ServiceException(Throwable cause, String errorId) {
        super(cause);
        this.errorId = errorId;
    }

    public String getErrorId() {
        return this.errorId;
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "errorId='" + errorId + '\'' +
                '}';
    }
}
