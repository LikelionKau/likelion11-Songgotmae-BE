package likelion.underdog.songgotmae.util.exception;

import lombok.Data;

public class MemberAlreadyExistException extends RuntimeException{
    public MemberAlreadyExistException(String message) {
        super(message);
    }
}
