package likelion.underdog.songgotmae.config.auth;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String kauEmail) throws UsernameNotFoundException {
        log.debug("DEBUG : loadUserByUsername 호출됨");
        Member memberPC = memberRepository.findByKauEmail(kauEmail).orElseThrow(
                () -> new InternalAuthenticationServiceException("로그인 실패")
        );
        return new LoginMember(memberPC);
    }
}
