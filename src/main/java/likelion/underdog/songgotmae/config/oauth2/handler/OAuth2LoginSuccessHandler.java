//package likelion.underdog.songgotmae.config.oauth2.handler;
//
//import likelion.underdog.songgotmae.config.auth.LoginMember;
//import likelion.underdog.songgotmae.config.jwt.JwtProcess;
//import likelion.underdog.songgotmae.config.jwt.JwtVO;
//import likelion.underdog.songgotmae.config.oauth2.CustomOAuth2User;
//import likelion.underdog.songgotmae.domain.member.Member;
//import likelion.underdog.songgotmae.domain.member.MemberRole;
//import likelion.underdog.songgotmae.domain.member.repository.MemberRepository;
//import likelion.underdog.songgotmae.util.exception.CustomGeneralException;
//import likelion.underdog.songgotmae.util.exception.CustomNotFoundException;
//import likelion.underdog.songgotmae.util.formatter.CustomResponseFormatter;
//import likelion.underdog.songgotmae.web.dto.member.MemberResponseDto;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Map;
//import java.util.Optional;
//
//@Slf4j
//@Component
//public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
//    private static final String INSERT_ADDITIONAL_INFO_URL = "oauth2/sign-up";
//
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        log.info("OAuth2 login 성공");
//        try {
//            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
//            if (oAuth2User.getRole().equals(MemberRole.SOCIAL_GUEST)) {
//                Member tempMember = Member.builder()
//                        .id(oAuth2User.getMemberId())
//                        .role(oAuth2User.getRole())
//                        .build();
//                LoginMember loginMember = new LoginMember(tempMember);
//                String accessToken = JwtProcess.create(loginMember);
//                response.addHeader(JwtVO.HEADER, accessToken);
//                response.sendRedirect(INSERT_ADDITIONAL_INFO_URL);
//                // kauEmail입력해야됨...
//                MemberResponseDto.LoginResponseDto loginResponseDto = new MemberResponseDto.LoginResponseDto(loginMember.getMember());
//                CustomResponseFormatter.success(response, loginResponseDto);
//            } else {
//                Member tempMember = Member.builder()
//                        .id(oAuth2User.getMemberId())
//                        .role(oAuth2User.getRole())
//                        .build();
//                LoginMember loginMember = new LoginMember(tempMember);
//                String accessToken = JwtProcess.create(loginMember);
//                response.addHeader(JwtVO.HEADER, accessToken);
//                MemberResponseDto.LoginResponseDto loginResponseDto = new MemberResponseDto.LoginResponseDto(loginMember.getMember());
//                CustomResponseFormatter.success(response, loginResponseDto);
//            }
//        } catch (CustomGeneralException e) {
//            throw e;
//        }
//
//
//
//    }
//
//}
