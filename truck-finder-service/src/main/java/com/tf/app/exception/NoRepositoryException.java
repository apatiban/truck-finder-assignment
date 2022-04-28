package com.tf.app.exception;

public class NoRepositoryException extends Exception {

    public NoRepositoryException() {
    }

    public NoRepositoryException(String message) {
        super(message);
    }

    public NoRepositoryException(Throwable cause) {
        super(cause);
    }

}
