package likelion.underdog.songgotmae.domain.post;

import org.springframework.stereotype.Service;


@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }
}
