package likelion.underdog.songgotmae.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
    ADMIN("관리자"), USER("일반 유저"), SOCIAL_GUEST("소셜로그인 최초접속자");
    private final String value;
}
