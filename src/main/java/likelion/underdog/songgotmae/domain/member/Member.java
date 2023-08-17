package likelion.underdog.songgotmae.domain.member;

import jakarta.persistence.*;
import likelion.underdog.songgotmae.domain.agreement.Agreement;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

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

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agreement> agreements; //cascade와 orphanRemoval 옵션을 설정하여
                                        // Member가 삭제될 때 관련된 모든 Agreement 인스턴스도 삭제되도록 하였다.
}
