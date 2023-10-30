package likelion.underdog.songgotmae.domain.member.service;

import likelion.underdog.songgotmae.web.dto.member.MemberJoinRequestDto;
import likelion.underdog.songgotmae.web.dto.member.MemberResponseDto;

public interface MemberJoinService {
    public MemberResponseDto.JoinResponseDto joinMember(MemberJoinRequestDto.JoinRequestDto joinRequest);
    public MemberResponseDto.JoinResponseDto joinAdminMember(MemberJoinRequestDto.JoinRequestDto joinRequest);

}
