package likelion.underdog.songgotmae.domain.post;

import javax.persistence.*;
import likelion.underdog.songgotmae.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "post_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member author;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 255)
    private String content;

    @Comment("운영자가 검열했는지 여부 ~ null : in queue, true : 찬성, false : 반대")
    private Boolean isApproved;

    private Long agreementCount;
    private Long disagreementCount;

    public void updateAgreementCounts(long agreementCount, long disagreementCount) {
        this.agreementCount = agreementCount;
        this.disagreementCount = disagreementCount;
    }

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;

    public void updateApprovedTrue() {
        this.isApproved = true;
    }
    public void updateApprovedFalse() {
        this.isApproved = false;
    }

    @Builder
    private Post(Member author, String title, String content, Boolean isApproved) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.isApproved = isApproved;
        this.agreementCount = 0L; //초기값 0 설정
        this.disagreementCount = 0L; //초기값 0 설정
    }
}

