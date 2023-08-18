package likelion.underdog.songgotmae.domain.agreement;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.post.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "agreement_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agreement_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;


    @Column(nullable = false)
    private Boolean isSupport; // isSupport가 true면 찬성 false면 반대

    @Builder
    public Agreement(Member member, Post post, Boolean isSupport) {
        this.member = member;
        this.post = post;
        this.isSupport = isSupport; // isSupport(찬성,반대)의 초기값을 null로 할당
    }

    public Boolean getIsSupport() {
        return isSupport;
    }

    public void setIsSupport(boolean isSupport) {
        this.isSupport = isSupport;
    }

}
