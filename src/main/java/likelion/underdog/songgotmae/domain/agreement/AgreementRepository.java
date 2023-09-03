package likelion.underdog.songgotmae.domain.agreement;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    long countByPostAndIsAgree(Post post, boolean isAgree);

    Optional<Agreement> findByMemberAndPost(Member member, Post post);

    List<Agreement> findByPost(Post post);
}
