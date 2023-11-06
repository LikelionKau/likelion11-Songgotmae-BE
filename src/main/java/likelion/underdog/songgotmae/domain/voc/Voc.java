package likelion.underdog.songgotmae.domain.voc;

import javax.persistence.*;

import likelion.underdog.songgotmae.domain.common.BaseTimeEntity;
import likelion.underdog.songgotmae.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "voc_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Voc extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voc_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member author;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 255)
    private String content;


    public static class VocBuilder {
        private Long id;
        private String title;
        private String content;

        private VocBuilder() {
        }

        public VocBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public VocBuilder title(String title) {
            this.title = title;
            return this;
        }

        public VocBuilder content(String content) {
            this.content = content;
            return this;
        }

        public Voc build() {
            Voc voc = new Voc();
            voc.id = id;
            voc.title = title;
            voc.content = content;
            return voc;
        }
    }

    public static VocBuilder builder() {
        return new VocBuilder();
    }
}

