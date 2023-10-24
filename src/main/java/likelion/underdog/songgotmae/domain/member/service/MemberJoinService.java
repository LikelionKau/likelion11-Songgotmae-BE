package likelion.underdog.songgotmae.domain.member.service;

import likelion.underdog.songgotmae.web.dto.member.MemberRequestDto;
import likelion.underdog.songgotmae.web.dto.member.MemberResponseDto;

public interface MemberJoinService {
    public MemberResponseDto.JoinResponseDto joinMember(MemberRequestDto.JoinRequestDto joinRequest);
    public MemberResponseDto.JoinResponseDto joinAdminMember(MemberRequestDto.JoinRequestDto joinRequest);

    /*
    * 로그인은 songgotmae.config.auth.loginservice 와 ../jwt/JwtAuthenticationFilter 등 security 레이어에서 수행중
    * */
}
