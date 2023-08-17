package likelion.underdog.songgotmae.domain.post;

import java.util.List;

public class PostService {
    private final PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public PostDto getPostDtoById(Long id) {
        PostDto postDto = findPostDtoById(id);
        if (postDto != null) {
            return postDto;
        } else {
            return null;
        }
}
