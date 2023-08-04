package likelion.underdog.songgotmae.domain.post;

import likelion.underdog.songgotmae.web.dto.PostDto;
import likelion.underdog.songgotmae.domain.post.PostRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public String newPost(PostDto postDto) {
        Post post = Post.from(postDto);
        postRepository.save(post);
        return null;
    }
}