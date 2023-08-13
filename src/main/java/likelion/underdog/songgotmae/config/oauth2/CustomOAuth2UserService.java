package likelion.underdog.songgotmae.config.oauth2;

import likelion.underdog.songgotmae.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;


    /*
    * OAuth2 인증을 완료한 후, 전달 받은 데이터(code)로 서비스에 접근할 수 있는 인증 정보를 생성하고
    * 사용자 정보를 가져온다. 이때, OAuth2User를 반환하면 Spring Security는 임시 세션에 저장함.
    **/
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> service = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = service.loadUser(userRequest); // OAuth2 정보를 가져옴

        Map<String, Object> originAttributes = oAuth2User.getAttributes(); // OAuth2User의 attribute

        // OAuth2 서비스 id (google, kakao, naver, ...)
        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 소셜 서버 정보 가져옴

        // OAuthAttributes : OAuth2User의 attribute를 서비스 유형에 맞게 담아줄 클래스
        OAuth2Attributes attributes = OAuth2Attributes.of(registrationId, originAttributes);


        return null;
    }
}
