package com.solvd.lab.chat.threaded.exception;

import java.io.IOException;

public class UnableToWriteException extends IOException {
    public UnableToWriteException(String message) {
        super(message);
    }
}
