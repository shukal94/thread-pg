package com.solvd.lab.chat.threaded.exception;

import java.io.IOException;

public class UnableToReadException extends IOException {
    public UnableToReadException(String message) {
        super(message);
    }
}
