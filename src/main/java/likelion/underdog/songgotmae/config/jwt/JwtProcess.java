package likelion.underdog.songgotmae.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import likelion.underdog.songgotmae.config.auth.LoginMember;
import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.member.MemberRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
public class JwtProcess {

    public static String create(LoginMember loginMember) {
        String jwtToken = JWT.create()
                .withSubject("Songgotmae")
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtVO.EXPIRATION_TIME))
                .withClaim("id", loginMember.getMember().getId())
                .withClaim("role", String.valueOf(loginMember.getMember().getRole()))
                .sign(Algorithm.HMAC512(JwtVO.SECRET_KEY));
        return JwtVO.TOKEN_PREFIX + jwtToken;
    }



    public static LoginMember verify(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(JwtVO.SECRET_KEY)).build().verify(token);
        Long id = decodedJWT.getClaim("id").asLong();
        String role = decodedJWT.getClaim("role").asString();

        /* 보완점 없는지 체크할 부분 */
        Member member = Member.builder()
                .id(id)
                .role(MemberRole.valueOf(role))
                .build();

        LoginMember loginMember = new LoginMember(member);
        return loginMember;
    }
}
