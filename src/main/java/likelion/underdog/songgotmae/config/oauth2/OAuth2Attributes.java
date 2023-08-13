package likelion.underdog.songgotmae.config.oauth2;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.member.MemberRole;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
public class OAuth2Attributes {
    private Map<String, Object> attributes; // OAuth2 반환하는 유저 정보
    private String nameAttributesKey; // 이게 뭐지??? @@@@@@@@@@@@@@@@
    private String name;
    private String email;
    private String gender;
    private String ageRange;
    private String profileImageUrl;

    @Builder
    public OAuth2Attributes(Map<String, Object> attributes, String nameAttributesKey, String name, String email, String gender, String ageRange, String profileImageUrl) {
        this.attributes = attributes;
        this.nameAttributesKey = nameAttributesKey;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.ageRange = ageRange;
        this.profileImageUrl = profileImageUrl;
    }

    public static OAuth2Attributes of(String socialName, Map<String, Object> attributes) {
        if (socialName.equals("google")) {
            return ofGoogle("sub", attributes);
        }
        return null;
    }

    private static OAuth2Attributes ofGoogle(String usernameAttributeName, Map<String, Object> attributes) {
        return OAuth2Attributes.builder()
                .name(String.valueOf(attributes.get("name")))
                .email(String.valueOf(attributes.get("email")))
                .profileImageUrl(String.valueOf(attributes.get("picture")))
                .attributes(attributes)
                .nameAttributesKey(usernameAttributeName)
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .nickname(name)
                .gmail(email)
                .role(MemberRole.USER)
                .build();
    }


}
