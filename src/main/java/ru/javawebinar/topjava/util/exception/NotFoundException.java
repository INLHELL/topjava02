package ru.javawebinar.topjava.util.exception;

/**
 * Created by vladislav.fedotov on 06.03.2015.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
