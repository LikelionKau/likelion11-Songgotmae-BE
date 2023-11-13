package likelion.underdog.songgotmae.util.handler;

import com.auth0.jwt.exceptions.SignatureVerificationException;
<<<<<<< HEAD
import likelion.underdog.songgotmae.util.exception.*;
=======
import likelion.underdog.songgotmae.util.exception.CustomForbiddenException;
import likelion.underdog.songgotmae.util.exception.CustomNotFoundException;
import likelion.underdog.songgotmae.util.exception.CustomValidationException;
import likelion.underdog.songgotmae.util.exception.MemberAlreadyExistException;
>>>>>>> UD-55
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
        return new ResponseEntity<>(new CommonResponseDto<>(-1, e.getMessage()/*, e*/), HttpStatus.NOT_FOUND); // 404
    }

    @ExceptionHandler(MemberAlreadyExistException.class)
    public ResponseEntity<?> memberAlreadyExistExcpetion(MemberAlreadyExistException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new CommonResponseDto<>(-1, e.getMessage()/*, e*/), HttpStatus.BAD_REQUEST); // 400
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> invalidRequestException(CustomValidationException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new CommonResponseDto<>(-1, e.getMessage()/*, e*/), HttpStatus.BAD_REQUEST); // 400
    }

    @ExceptionHandler(CustomForbiddenException.class)
    public ResponseEntity<?> apiException(CustomForbiddenException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new CommonResponseDto<>(-1, e.getMessage()/*, e*/), HttpStatus.FORBIDDEN); // 403
    }
/*
* 의도대로 동작 x
* */
    @ExceptionHandler(SignatureVerificationException.class)
    public ResponseEntity<?> invalidTokenException(InvalidTokenException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new CommonResponseDto<>(-1, e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
