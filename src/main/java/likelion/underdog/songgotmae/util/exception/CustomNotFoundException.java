package likelion.underdog.songgotmae.util.exception;

import lombok.Data;

public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String message) {
        super(message);
    }
}
