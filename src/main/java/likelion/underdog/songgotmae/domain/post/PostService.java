package likelion.underdog.songgotmae.domain.post;

<<<<<<< HEAD
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
=======
import likelion.underdog.songgotmae.web.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto.SaveResponseDto createPost(PostDto.CreateRequestDto requestBody);
    PostDto.SaveResponseDto approvePostTrue(Long id);
    PostDto.SaveResponseDto approvePostFalse(Long id);
    List<PostDto.FindResponseDto> findAllPosts();
    List<PostDto.FindResponseDto> findApprovedPosts();
    List<PostDto.FindResponseDto> findMemberPosts(Long memberId);
>>>>>>> dev
}
