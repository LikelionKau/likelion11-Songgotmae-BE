package likelion.underdog.songgotmae.domain.post;

import likelion.underdog.songgotmae.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public PostDto getPostDtoById(Long postId) {
        Post post = findPostById(postId);
        if (post != null) {
            return new PostDto(post);
        } else {
            return null;
        }
    }

    public PostDto approvePostTrue(Long postId) {
        Post post = findPostById(postId);
        if (post != null) {
            post.setApprovedTrue();
            post.setMessage("게시글이 승인되었습니다.");
            return new PostDto(post);
        } else {
            return null; // 게시글을 찾을 수 없을 때는 null 반환
        }
    }

    public PostDto approvePostFalse(Long postId) {
        Post post = findPostById(postId);
        if (post != null) {
            post.setApprovedFalse();
            post.setMessage("게시글이 거부되었습니다.");
            return new PostDto(post);
        } else {
            return null; // 게시글을 찾을 수 없을 때는 null 반환
        }
    }

    private Post findPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }
}