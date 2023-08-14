package likelion.underdog.songgotmae.domain.member.repository;

import likelion.underdog.songgotmae.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    /*
    * 이미 생성된 사용자인지 처음 가입하는 사용자인지 판단하기 위함
    **/
    Optional<Member> findByKauEmail(String kauEmail);

    Optional<Member> findByNickname(String nickname);

    Optional<Member> findBySocialEmail(String socialEmail);
}
