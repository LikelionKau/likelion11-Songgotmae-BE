package likelion.underdog.songgotmae.domain.member.service;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.member.repository.MemberRepository;
import likelion.underdog.songgotmae.util.exception.CustomNotFoundException;
import likelion.underdog.songgotmae.util.exception.MemberAlreadyExistException;
import likelion.underdog.songgotmae.web.dto.member.MemberJoinRequestDto;
import likelion.underdog.songgotmae.web.dto.member.MemberPwChangeRequestDto;
import likelion.underdog.songgotmae.web.dto.member.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class memberServiceImpl implements memberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public MemberResponseDto.JoinResponseDto joinMember(MemberJoinRequestDto joinRequest) {
        log.info("INFO : 회원가입 진행");
        checkDuplicateMember(joinRequest);
        Member saveMember = memberRepository.save(joinRequest.toEntity(passwordEncoder));
        return new MemberResponseDto.JoinResponseDto(saveMember);
    }


    @Override
    @Transactional
    public MemberResponseDto.JoinResponseDto joinAdminMember(MemberJoinRequestDto joinRequest) {
        log.info("INFO : 관리자 회원가입 진행");
        checkDuplicateMember(joinRequest);
        Member saveMember = memberRepository.save(joinRequest.toAdminEntity(passwordEncoder));
        return new MemberResponseDto.JoinResponseDto(saveMember);
    }

    @Override
    @Transactional
    public MemberResponseDto.CommonResponseDto changeMemberPassword(MemberPwChangeRequestDto pwChangeRequest, String email) {
        log.info("INFO : 회원 패스워드 변경 진행");
        Member findMember = memberRepository.findByKauEmail(email).orElseThrow(() -> new CustomNotFoundException("가입 정보가 없습니다."));
        Member changeMember = findMember.setPassword(pwChangeRequest.encode(passwordEncoder));
        memberRepository.flush();
        return new MemberResponseDto.CommonResponseDto(changeMember);
    }

    // ----- 내부메서드 -----
    private void checkDuplicateMember(MemberJoinRequestDto joinRequest) {
        Optional<Member> findMember = memberRepository.findByKauEmail(joinRequest.getKauEmail());
        if (findMember.isPresent()) {
            throw new MemberAlreadyExistException("이미 가입된 이메일입니다.");
        }
    }

}
