package io.dev.simpleblog.exception;

public class NotFoundException extends BusinessException{

    private static final String MESSAGE_FORMAT = "%s not found (value: %s)";

    public NotFoundException() {
    }

    public NotFoundException(Class<?> target, Object value) {
        super(String.format(MESSAGE_FORMAT, target.getSimpleName(), value));
    }

}
