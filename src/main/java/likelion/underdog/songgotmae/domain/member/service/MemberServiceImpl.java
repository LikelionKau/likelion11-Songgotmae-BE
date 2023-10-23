package likelion.underdog.songgotmae.domain.member.service;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.member.repository.MemberRepository;
import likelion.underdog.songgotmae.util.exception.MemberAlreadyExistException;
import likelion.underdog.songgotmae.web.dto.member.LoginRequestDto;
import likelion.underdog.songgotmae.web.dto.member.MemberRequestDto;
import likelion.underdog.songgotmae.web.dto.member.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public MemberResponseDto.JoinResponseDto joinMember(MemberRequestDto.JoinRequestDto joinRequest) {
        log.info("INFO : 회원가입 진행");
        Optional<Member> findMember = memberRepository.findByKauEmail(joinRequest.getKauEmail());
        if (findMember.isPresent()) {
            throw new MemberAlreadyExistException("이미 가입된 이메일입니다.");
        }
        Member memberPC = memberRepository.save(joinRequest.toEntity(passwordEncoder));
        return new MemberResponseDto.JoinResponseDto(memberPC);
    }

    @Override
    @Transactional
    public MemberResponseDto.JoinResponseDto joinAdminMember(MemberRequestDto.JoinRequestDto joinRequest) {
        log.info("INFO : 관리자 회원가입 진행");
        Optional<Member> findMember = memberRepository.findByKauEmail(joinRequest.getKauEmail());
        if (findMember.isPresent()) {
            throw new MemberAlreadyExistException("이미 가입된 이메일입니다.");
        }
        Member memberPC = memberRepository.save(joinRequest.toAdminEntity(passwordEncoder));
        return new MemberResponseDto.JoinResponseDto(memberPC);
    }

    @Override
    @Transactional
    public MemberResponseDto.LoginResponseDto loginMember(LoginRequestDto request) {
        log.info("INFO : 로그인 진행");
        Member findMember = memberRepository.findByKauEmail(request.getUsername()).get();
        if (!request.match(passwordEncoder, findMember.getPassword())) {
            throw new NoSuchElementException("패스워드 터짐. (현재 예외가 올바르지 못하니 추가작업 필요함).");
        }

        return new MemberResponseDto.LoginResponseDto(findMember);
    }
}
