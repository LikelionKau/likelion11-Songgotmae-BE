package likelion.underdog.songgotmae.util.auth;

import likelion.underdog.songgotmae.util.exception.NotAuthenticatedException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@NoArgsConstructor
public class SecurityUtil {
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Long) {
            return (Long) authentication.getPrincipal();
        } else {
            throw new NotAuthenticatedException();
        }
    }
}
