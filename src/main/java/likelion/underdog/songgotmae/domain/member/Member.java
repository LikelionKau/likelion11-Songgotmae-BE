package likelion.underdog.songgotmae.domain.member;

<<<<<<< HEAD
import jakarta.persistence.*;
import likelion.underdog.songgotmae.domain.agreement.Agreement;
=======
import javax.persistence.*;

>>>>>>> dev
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

<<<<<<< HEAD
import java.util.List;
=======
import java.time.LocalDateTime;
>>>>>>> dev

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "member_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

<<<<<<< HEAD
    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agreement> agreements; //cascade와 orphanRemoval 옵션을 설정하여
                                        // Member가 삭제될 때 관련된 모든 Agreement 인스턴스도 삭제되도록 하였다.
=======
    @Comment("영문/숫자 2~20자 이내")
    @Column(name = "nickname", unique = true, nullable = false, length = 20)
    private String nickname;

    @Comment("길이 4~20자 이내") // 인코딩 이후 60자
    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Comment("이메일 형식")
    @Column(name = "kau_email", unique = true, nullable = false, length = 30)
    private String kauEmail;

    @Comment("이메일 형식")
    @Column(name = "social_email", length = 30)
    private String socialEmail;


    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private MemberRole role;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @Builder
    public Member(Long id, String nickname, String password, String kauEmail, String socialEmail, MemberRole role) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.kauEmail = kauEmail;
        this.socialEmail = socialEmail;
        this.role = role;
    }

    // TODO : (1) 구글 로그인 시 유저 정보 입력 빌더, (2) 일반 로그인 시, 추후 구글 아이디 연결
>>>>>>> dev
}
