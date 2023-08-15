package likelion.underdog.songgotmae.domain.member.service;

import likelion.underdog.songgotmae.web.dto.member.MemberRequestDto;
import likelion.underdog.songgotmae.web.dto.member.MemberResponseDto;

public interface MemberService {
    public MemberResponseDto.JoinResponseDto joinMember(MemberRequestDto.JoinRequestDto joinRequest);
}
