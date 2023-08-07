package likelion.underdog.songgotmae.domain.post;

import org.springframework.stereotype.Service;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void newPost(Post postDto) {
        Post post = Post.from(postDto);
        postRepository.save(post);
    }
}