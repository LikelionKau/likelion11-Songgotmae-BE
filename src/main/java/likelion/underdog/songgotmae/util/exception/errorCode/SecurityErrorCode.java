package likelion.underdog.songgotmae.util.exception.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SecurityErrorCode implements BaseErrorCode {
    NOT_AUTHENTICATED(401, "사용자가 인증되지 않았습니다.");

    private final int httpStatusCode;
    private final String message;
}
