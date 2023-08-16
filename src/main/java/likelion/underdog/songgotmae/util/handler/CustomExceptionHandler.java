package likelion.underdog.songgotmae.util.handler;

import likelion.underdog.songgotmae.util.exception.CustomForbiddenException;
import likelion.underdog.songgotmae.util.exception.CustomNotFoundException;
import likelion.underdog.songgotmae.util.exception.CustomValidationException;
import likelion.underdog.songgotmae.util.exception.MemberAlreadyExistException;
import likelion.underdog.songgotmae.web.dto.CommonResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<?> notFoundExcpetion(CustomNotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new CommonResponseDto<>(-1, e.getMessage(), e), HttpStatus.NOT_FOUND); // 404
    }

    @ExceptionHandler(MemberAlreadyExistException.class)
    public ResponseEntity<?> memberAlreadyExistExcpetion(MemberAlreadyExistException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new CommonResponseDto<>(-1, e.getMessage(), e), HttpStatus.BAD_REQUEST); // 400
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> validationException(CustomValidationException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new CommonResponseDto<>(-1, e.getMessage(), e), HttpStatus.BAD_REQUEST); // 400
    }

    @ExceptionHandler(CustomForbiddenException.class) // 이 예외가 터지면 아래 메서드가 수행됨
    public ResponseEntity<?> apiException(CustomForbiddenException e) {
        log.error(e.getMessage());
        // code는 성공하면 1, 실패하면 -1이므로 -1 반환 && data는 줄 게 없으므로 null
        return new ResponseEntity<>(new CommonResponseDto<>(-1, e.getMessage(), e), HttpStatus.FORBIDDEN); // 403
    }

}
