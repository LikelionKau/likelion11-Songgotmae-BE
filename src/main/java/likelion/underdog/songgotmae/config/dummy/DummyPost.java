package likelion.underdog.songgotmae.config.dummy;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.post.Post;

public class DummyPost {

    protected Post newMockPost(Member author, String title, String content) {
        return Post.builder()
                .build();
    }
}
