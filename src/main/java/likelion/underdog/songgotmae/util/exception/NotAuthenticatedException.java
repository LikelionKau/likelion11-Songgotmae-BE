package likelion.underdog.songgotmae.util.exception;

import likelion.underdog.songgotmae.util.exception.errorCode.SecurityErrorCode;

public class NotAuthenticatedException extends BaseException{
    public NotAuthenticatedException() {
        super(SecurityErrorCode.NOT_AUTHENTICATED);
    }
}
