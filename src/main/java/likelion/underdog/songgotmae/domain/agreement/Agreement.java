package likelion.underdog.songgotmae.domain.agreement;

import javax.persistence.*;
import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.post.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "agreement_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Agreement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agreement_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false)
    private Boolean isSupport;
}
