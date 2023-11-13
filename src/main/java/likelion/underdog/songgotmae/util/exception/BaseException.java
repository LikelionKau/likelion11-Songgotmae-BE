package likelion.underdog.songgotmae.util.exception;

import likelion.underdog.songgotmae.util.exception.errorCode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private final BaseErrorCode errorCode;
}
