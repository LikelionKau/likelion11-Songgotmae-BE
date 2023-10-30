package likelion.underdog.songgotmae.domain.post;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.member.MemberRole;
import likelion.underdog.songgotmae.domain.member.repository.MemberRepository;
import likelion.underdog.songgotmae.web.dto.PostDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class PostServiceTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostService postService;

//    @AfterEach
//    void tearDown() {
//        postRepository.deleteAllInBatch();
//        memberRepository.deleteAllInBatch();
//    }


    @DisplayName("키워드를 입력하지 않으면 모든 게시글을 기준으로 paging해서 조회한다.")
    @Test
    public void findPostByNullKeywords() throws Exception {
        //given
        Member m = getMember("1", "hek", "kau@kau.ke", MemberRole.USER, "kau@google.com", "hello");
        Member saved = memberRepository.save(m);
        Post p1 = getPost("그냥 제목", saved, "내용1");
        Post p2 = getPost("그냥 제목", saved, "내용2");
        Post p3 = getPost("그냥 제목", saved, "내용3");
        Post p4 = getPost("그냥 제목", saved, "내용4");
        Post p5 = getPost("그냥 제목", saved, "내용5");
        Post p6 = getPost("키워드를 포함한 제목", saved, "내용6");
        Post p7 = getPost("키워드를 포함한 제목", saved, "내용7");
        Post p8 = getPost("키워드를 포함한 제목", saved, "내용8");
        Post p9 = getPost("키워드를 포함한 제목", saved, "내용9");
        Post p10 = getPost("키워드를 포함한 제목", saved, "내용10");
        postRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));

        PostDto.PostSearchRequestDto pageRequest = PostDto.PostSearchRequestDto.builder()
                .keyword(null)
                .page(0)
                .size(10)
                .build();

        //when
        Page<Post> posts = postService.searchPost(pageRequest);

        //then
        assertThat(posts).hasSize(10)
                .extracting("title", "content")
                .containsExactly(
                        tuple("그냥 제목", "내용1"),
                        tuple("그냥 제목", "내용2"),
                        tuple("그냥 제목", "내용3"),
                        tuple("그냥 제목", "내용4"),
                        tuple("그냥 제목", "내용5"),
                        tuple("키워드를 포함한 제목", "내용6"),
                        tuple("키워드를 포함한 제목", "내용7"),
                        tuple("키워드를 포함한 제목", "내용8"),
                        tuple("키워드를 포함한 제목", "내용9"),
                        tuple("키워드를 포함한 제목", "내용10")
                );
    }

    @DisplayName("키워드를 입력하면 제목에 키워드가 포함된 게시글만 paging하여 조회한다.")
    @Test
    public void findPostByKeywords() throws Exception {
        //given
        Member m = getMember("1", "hek", "kau@kau.ke", MemberRole.USER, "kau@google.com", "hello");
        Member saved = memberRepository.save(m);
        Post p2 = getPost("그냥 제목", saved, "내용2");
        Post p3 = getPost("그냥 제목", saved, "내용3");
        Post p1 = getPost("그냥 제목", saved, "내용1");
        Post p4 = getPost("그냥 제목", saved, "내용4");
        Post p5 = getPost("그냥 제목", saved, "내용5");
        Post p6 = getPost("키워드를 포함한 제목", saved, "내용6");
        Post p7 = getPost("키워드를 포함한 제목", saved, "내용7");
        Post p8 = getPost("키워드를 포함한 제목", saved, "내용8");
        Post p9 = getPost("키워드를 포함한 제목", saved, "내용9");
        Post p10 = getPost("키워드를 포함한 제목", saved, "내용10");
        postRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));

        String keyword = "키워드";

        int page = 0;
        int size = 3;
        PostDto.PostSearchRequestDto pageRequest = PostDto.PostSearchRequestDto.builder()
                .keyword(keyword)
                .page(page)
                .size(size)
                .build();

        //when
        Page<Post> posts = postService.searchPost(pageRequest);

        //then
        assertThat(posts).hasSize(size)
                .extracting("title", "content")
                .containsExactly(
                        tuple("키워드를 포함한 제목", "내용6"),
                        tuple("키워드를 포함한 제목", "내용7"),
                        tuple("키워드를 포함한 제목", "내용8")
                );
    }

    @DisplayName("키워드가 비어있으면 모든 게시글을 paging하여 조회한다.")
    @Test
    public void findPostByEmptyKeywords() throws Exception {
        //given
        Member m = getMember("1", "hek", "kau@kau.ke", MemberRole.USER, "kau@google.com", "hello");
        Member saved = memberRepository.save(m);
        Post p1 = getPost("그냥 제목", saved, "내용1");
        Post p2 = getPost("그냥 제목", saved, "내용2");
        Post p3 = getPost("그냥 제목", saved, "내용3");
        Post p4 = getPost("그냥 제목", saved, "내용4");
        Post p5 = getPost("그냥 제목", saved, "내용5");
        Post p6 = getPost("키워드를 포함한 제목",saved, "내용6");
        Post p7 = getPost("키워드를 포함한 제목", saved, "내용7");
        Post p8 = getPost("키워드를 포함한 제목", saved, "내용8");
        Post p9 = getPost("키워드를 포함한 제목", saved, "내용9");
        Post p10 = getPost("키워드를 포함한 제목", saved, "내용10");
        postRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));

        String keyword = "";
        int page = 0;
        int size = 10;
        PostDto.PostSearchRequestDto pageRequest = PostDto.PostSearchRequestDto.builder()
                .keyword(keyword)
                .page(page)
                .size(size)
                .build();

        //when
        Page<Post> posts = postService.searchPost(pageRequest);

        //then
        assertThat(posts).hasSize(10)
                .extracting("title", "content")
                .containsExactly(
                        tuple("그냥 제목", "내용1"),
                        tuple("그냥 제목", "내용2"),
                        tuple("그냥 제목", "내용3"),
                        tuple("그냥 제목", "내용4"),
                        tuple("그냥 제목", "내용5"),
                        tuple("키워드를 포함한 제목", "내용6"),
                        tuple("키워드를 포함한 제목", "내용7"),
                        tuple("키워드를 포함한 제목", "내용8"),
                        tuple("키워드를 포함한 제목", "내용9"),
                        tuple("키워드를 포함한 제목", "내용10")
                );
    }

    /* ----- 내부 편의 메서드 ----- */

    private static Post getPost(String title, Member m, String content) {
        return Post.builder()
                .title(title)
                .author(m)
                .content(content)
                .build();
    }

    private static Member getMember(String id, String nickname, String kauEmail, MemberRole memberRole, String socialEmail, String password) {
        return Member.builder()
                .id(Long.parseLong(id))
                .nickname(nickname)
                .kauEmail(kauEmail)
                .role(memberRole)
                .socialEmail(socialEmail)
                .password(password)
                .build();
    }
}
