package likelion.underdog.songgotmae.domain.member.service;

import likelion.underdog.songgotmae.web.dto.member.MemberJoinRequestDto;
import likelion.underdog.songgotmae.web.dto.member.MemberPwChangeRequestDto;
import likelion.underdog.songgotmae.web.dto.member.MemberResponseDto;

public interface memberService {
    public MemberResponseDto.JoinResponseDto joinMember(MemberJoinRequestDto joinRequest);
    public MemberResponseDto.JoinResponseDto joinAdminMember(MemberJoinRequestDto joinRequest);

    public MemberResponseDto.CommonResponseDto changeMemberPassword(MemberPwChangeRequestDto pwChangeRequest, String email);
}
