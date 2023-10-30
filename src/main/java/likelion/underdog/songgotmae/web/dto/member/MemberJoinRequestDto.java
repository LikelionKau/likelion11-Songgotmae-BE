package likelion.underdog.songgotmae.web.dto.member;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.member.MemberRole;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MemberJoinRequestDto {
    @Data
    public static class JoinRequestDto {

        @NotEmpty
        @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,20}$", message = "한글/영문/숫자 2~20자 이내로 작성해주세요.")
        private String nickname;

        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9]{2,25}@kau\\.kr", message = "항공대 학생 이메일 형식으로 작성해주세요.")
        private String kauEmail;

        @NotEmpty
        @Size(min = 4, max = 20)
        private String password;

        public Member toEntity(BCryptPasswordEncoder passwordEncoder) {
            return Member.builder()
                    .nickname(nickname)
                    .password(passwordEncoder.encode(password))
                    .kauEmail(kauEmail)
                    .role(MemberRole.USER)
                    .build();
        }
        public Member toAdminEntity(BCryptPasswordEncoder passwordEncoder) {
            return Member.builder()
                    .nickname(nickname)
                    .password(passwordEncoder.encode(password))
                    .kauEmail(kauEmail)
                    .role(MemberRole.ADMIN)
                    .build();
        }
    }

}
