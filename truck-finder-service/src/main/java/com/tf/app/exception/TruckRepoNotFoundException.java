package com.tf.app.exception;

public class TruckRepoNotFoundException extends Exception {

    public TruckRepoNotFoundException() {
    }

    public TruckRepoNotFoundException(String message) {
        super(message);
    }

    public TruckRepoNotFoundException(Throwable cause) {
        super(cause);
    }

}
