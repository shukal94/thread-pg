package com.solvd.lab.chat.threaded.exception;

import java.io.IOException;

public class BadExtensionException extends IOException {
    public BadExtensionException(String message) {
        super(message);
    }
}
