package likelion.underdog.songgotmae.domain.post;

import jakarta.persistence.*;
import likelion.underdog.songgotmae.domain.member.Member;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import likelion.underdog.songgotmae.web.dto.PostDto;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "post_tb")
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

    public Post() {
    }

    public static Post from(PostDto postDto) {
        Post post = new Post();
        post.setNumber(postDto.getNumber());
        post.setUserId(postDto.getUserId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContents());
        return post;
    }
}