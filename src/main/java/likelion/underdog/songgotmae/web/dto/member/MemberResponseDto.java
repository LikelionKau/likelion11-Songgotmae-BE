package likelion.underdog.songgotmae.web.dto.member;

import likelion.underdog.songgotmae.domain.member.LoginMember;
import likelion.underdog.songgotmae.domain.member.Member;
import lombok.Data;

public class MemberResponseDto {

    @Data
    public static class JoinResponseDto {
        private Long id;
        private String kauEmail;

        public JoinResponseDto(Member member) {
            this.id = member.getId();
            this.kauEmail = member.getKauEmail();
        }
    }


    @Data
    public static class LoginResponseDto {
        private Long id;

        public LoginResponseDto(LoginMember loginMember) {
            this.id = loginMember.getMember().getId();
        }
    }
}
