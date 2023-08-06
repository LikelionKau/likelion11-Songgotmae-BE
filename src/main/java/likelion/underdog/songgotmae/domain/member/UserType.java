package likelion.underdog.songgotmae.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {
    ADMIN("관리자"), USER("일반 유저");
    private String value;
}
