package likelion.underdog.songgotmae.domain.post;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.member.MemberRole;
import likelion.underdog.songgotmae.domain.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;

//    @Test
//    void findAllPosts() {
//    }
//
//    @Test
//    void findApprovedPosts() {
//    }
//
//    @Test
//    void findNotApprovedPosts() {
//    }
//
//    @Test
//    void findPostsByMemberId() {
//    }

    @DisplayName("검색어를 입력하면 해당 키워드를 제목에 포함하는 게시글을 조회한다.")
    @Test
    void findByTitleContaining() {
        // given
        Member m = getMember("1", "hek", "kau@kau.ke", "kau@google.com", "hello", MemberRole.USER);
        Post p1 = getPost("그냥 제목", m, "내용1");
        Post p2 = getPost("키워드를 포함한 제목", m, "내용2");
        Post p3 = getPost("키워드를 포함한 제목", m, "내용3");
        Post p4 = getPost("키워드를 포함한 제목", m, "내용4");
        Post p5 = getPost("키워드를 포함한 제목", m, "내용5");
        Post p6 = getPost("키워드를 포함한 제목", m, "내용6");
        Post p7 = getPost("키워드를 포함한 제목", m, "내용7");
        Post p8 = getPost("키워드를 포함한 제목", m, "내용8");
        Post p9 = getPost("키워드를 포함한 제목", m, "내용9");
        Post p10 = getPost("키워드를 포함한 제목", m, "내용10");
        memberRepository.save(m);
        postRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
        Pageable pageable0 = PageRequest.of(0, 5);
        Pageable pageable1 = PageRequest.of(1, 5);
        String keyword = "키워드";

        // when
        Page<Post> posts1 = postRepository.findByTitleContaining(keyword, pageable0);
        Page<Post> posts2 = postRepository.findByTitleContaining(keyword, pageable1);

        // then
        Assertions.assertThat(posts1).hasSize(5)
                .extracting("title", "content")
                .containsExactlyInAnyOrder(
                        tuple("키워드를 포함한 제목", "내용2"),
                        tuple("키워드를 포함한 제목", "내용3"),
                        tuple("키워드를 포함한 제목", "내용4"),
                        tuple("키워드를 포함한 제목", "내용5"),
                        tuple("키워드를 포함한 제목", "내용6")
                );
        Assertions.assertThat(posts2).hasSize(4)
                .extracting("title", "content")
                .containsExactlyInAnyOrder(
                        tuple("키워드를 포함한 제목", "내용7"),
                        tuple("키워드를 포함한 제목", "내용8"),
                        tuple("키워드를 포함한 제목", "내용9"),
                        tuple("키워드를 포함한 제목", "내용10")
                );

    }

    private static Member getMember(String id, String nickname, String kauEmail, String socialEmail, String password, MemberRole role) {
        return Member.builder()
                .id(Long.parseLong(id))
                .nickname(nickname)
                .kauEmail(kauEmail)
                .role(role)
                .socialEmail(socialEmail)
                .password(password)
                .build();
    }

    private static Post getPost(String title, Member m, String content) {
        return Post.builder()
                .title(title)
                .author(m)
                .content(content)
                .build();
    }
}
