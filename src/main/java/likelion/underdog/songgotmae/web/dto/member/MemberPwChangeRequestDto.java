package likelion.underdog.songgotmae.web.dto.member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberPwChangeRequestDto {
    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$",
            message = "비밀번호는 영문/숫자/특수문자 최소 1개씩 포함, 8~16자로 설정해주세요. (특수문자 일부 제외)")
    private String newPassword;

    public MemberPwChangeRequestDto encode(BCryptPasswordEncoder passwordEncoder) {
        String encodedPassword = passwordEncoder.encode(this.newPassword);
        this.setNewPassword(encodedPassword);
        return this;
    }
}
