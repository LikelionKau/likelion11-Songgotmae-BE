package likelion.underdog.songgotmae.domain.post;
import likelion.underdog.songgotmae.web.dto.PostDto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>();
    public List<Post> getPosts() {
        return posts;
    }
    public PostDto getPostDtoById(Long postId) {
        Post post = findPostById(postId);
        if (post != null) {
            return new PostDto(post);
        } else {
            return null;
        }
    }

    public String approvePost(Long postId) {
        Post post = findPostById(postId);
        if (post != null) {
            post.setApproved(true);
            return "게시글이 승인되었습니다.";
        } else {
            return "게시글을 찾을 수 없습니다.";
        }
    }

    public String disapprovePost(Long postId) {
        Post post = findPostById(postId);
        if (post != null) {
            post.setApproved(false);
            return "게시글이 거부되었습니다.";
        } else {
            return "게시글을 찾을 수 없습니다.";
        }
    }

    private Post findPostById(Long postId) {
        for (Post post : posts) {
            if (post.getId().equals(postId)) {
                return post;
            }
        }
        return null;
    }
}