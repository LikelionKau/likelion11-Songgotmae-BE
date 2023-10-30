<<<<<<<< HEAD:src/main/java/likelion/underdog/songgotmae/util/jwt/JwtProcess.java
package likelion.underdog.songgotmae.util.jwt;
========
package likelion.underdog.songgotmae.domain.member.service;
>>>>>>>> dev:src/main/java/likelion/underdog/songgotmae/domain/member/service/JwtProcess.java

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import likelion.underdog.songgotmae.domain.member.LoginMember;
import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.member.MemberRole;
<<<<<<<< HEAD:src/main/java/likelion/underdog/songgotmae/util/jwt/JwtProcess.java
import likelion.underdog.songgotmae.util.constant.JwtVO;
========
>>>>>>>> dev:src/main/java/likelion/underdog/songgotmae/domain/member/service/JwtProcess.java
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JwtProcess {

    public static String create(LoginMember loginMember) {
        Date twoHoursAfterNow = new Date(System.currentTimeMillis() + JwtVO.EXPIRATION_TIME);
        Long loginMemberId = loginMember.getMember().getId();
        String loginMemberRole = String.valueOf(loginMember.getMember().getRole());

        String jwtToken = JWT.create()
                .withSubject("Songgotmae")
                .withExpiresAt(twoHoursAfterNow)
                .withClaim("id", loginMemberId)
                .withClaim("role", loginMemberRole)
                .sign(Algorithm.HMAC512(JwtVO.SECRET_KEY));
        return JwtVO.BEARER_PREFIX + jwtToken;
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
