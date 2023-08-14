package likelion.underdog.songgotmae.config.oauth2.handler;

import likelion.underdog.songgotmae.util.formatter.CustomResponseFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class OAuth2LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.debug("DEBUG : onAuthenticationFailure 호출됨 - 소셜 로그인 실패");
        CustomResponseFormatter.fail(response, "로그인에 실패하였습니다.", exception, HttpStatus.UNAUTHORIZED);
    }
    
}
