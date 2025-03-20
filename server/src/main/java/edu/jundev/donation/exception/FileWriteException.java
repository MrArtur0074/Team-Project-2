package edu.jundev.donation.exception;

public class FileWriteException extends RuntimeException {
    public FileWriteException() {
    }

    public FileWriteException(String message) {
        super(message);
    }
}
