//package likelion.underdog.songgotmae.config.oauth2;
//
//import likelion.underdog.songgotmae.config.oauth2.userInfo.GoogleOAuth2UserInfo;
//import likelion.underdog.songgotmae.config.oauth2.userInfo.OAuth2UserInfo;
//import likelion.underdog.songgotmae.domain.member.Member;
//import likelion.underdog.songgotmae.domain.member.MemberRole;
//import lombok.Builder;
//import lombok.Data;
//
//import java.util.Map;
//import java.util.UUID;
//
///*
//* 각 소셜에서 받아오는 데이터가 다르므로
//* 소셜 서비스별로 데이터를 받는 데이터를 분기 처리하는 DTO
//**/
//@Data
//public class OAuthAttributes {
//    private String nameAttributesKey; // OAuth2 로그인 진행 시 키가 되는 필드 값 (PK)
//    private OAuth2UserInfo oAuth2UserInfo; // 소셜 타입별 로그인 유저 정보(닉네임, 이메일, 프로필 사진 등등)
//
//    @Builder
//    public OAuthAttributes(String nameAttributesKey, OAuth2UserInfo oAuth2UserInfo) {
//        this.nameAttributesKey = nameAttributesKey;
//        this.oAuth2UserInfo = oAuth2UserInfo;
//    }
//
//    public static OAuthAttributes of(SocialType socialType, String usernameAttributeName , Map<String, Object> attributes) {
//        if (socialType.equals(SocialType.GOOGLE)) {
//            return ofGoogle(usernameAttributeName, attributes); // usernameAttributeName == "sub";
//        }
//        return null; // TODO : KAKAO, NAVER
//    }
//
//    private static OAuthAttributes ofGoogle(String usernameAttributeName, Map<String, Object> attributes) {
//        return OAuthAttributes.builder()
//                .nameAttributesKey(usernameAttributeName)
//                .oAuth2UserInfo(new GoogleOAuth2UserInfo(attributes))
//                .build();
//    }
//
//    // TODO : KAKAO, NAVER
//
//
//    public Member toEntity(SocialType socialType, OAuth2UserInfo oAuth2UserInfo) {
//        return Member.builder()
//                .nickname(oAuth2UserInfo.getNickname())
//                .socialEmail(UUID.randomUUID()+"@socialMember.com") // ${randomUUID}@socialMember.com
//                .role(MemberRole.SOCIAL_GUEST)
//                .build();
//        // 추가로 kauEmail이랑 password 받아야 함.
//        // 소셜 로그인 시에는 닉네임으로 unique 비교해서 엑세스토큰 주면 됨
//    }
//
//}
