package likelion.underdog.songgotmae.domain.post;

import jakarta.persistence.*;
import likelion.underdog.songgotmae.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @Column(nullable = false)
    private Long viewCount;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;

    @Column(nullable = false)
    private Boolean isApproved;  // isApproved 필드 추가

    @Column(nullable = false)
    private String message;  // message 필드 추가

    public Post(Member author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.viewCount = 0L;
        this.isApproved = null;  //생성자 생성 후 초기화
        this.message = null;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setApprovedTrue() {
        isApproved = true;
    }

    public void setApprovedFalse() {
        isApproved = false;
    }                              //파라미터 없이 함수 설정
}

