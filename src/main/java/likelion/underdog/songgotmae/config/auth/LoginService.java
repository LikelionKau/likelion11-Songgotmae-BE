package likelion.underdog.songgotmae.config.auth;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.member.repository.MemberRepository;
import likelion.underdog.songgotmae.util.exception.CustomNotFoundException;
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
                () -> new CustomNotFoundException("해당 이메일이 존재하지 않습니다.")
        );
        return new LoginMember(memberPC);
    }
}
