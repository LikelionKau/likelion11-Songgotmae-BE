package likelion.underdog.songgotmae.domain.member.service;

import likelion.underdog.songgotmae.domain.member.LoginMember;
import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.member.repository.MemberRepository;
import likelion.underdog.songgotmae.util.exception.CustomNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberLoginService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public LoginMember loadUserByUsername(String kauEmail) throws UsernameNotFoundException {
        log.debug("DEBUG : loadUserByUsername 호출됨");
        Member loginMember = memberRepository.findByKauEmail(kauEmail).orElseThrow(
                () -> new CustomNotFoundException("해당 이메일이 존재하지 않습니다.")
        );
        return new LoginMember(loginMember);
    }

    // TODO : 소셜로그인
}
