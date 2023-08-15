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

    public PostDto getPostDtoById(Long id) {
        PostDto postDto = findPostDtoById(id);
        if (postDto != null) {
            return postDto;
        } else {
            return null;
        }
    }

    public PostDto approvePostTrue(Long id) {
        PostDto postDto = findPostDtoById(id);
        if (postDto != null) {
            postDto.setApprovedTrue();
            postDto.setMessage("게시글이 승인되었습니다.");
            return postDto;
        } else {
            return null; // 게시글을 찾을 수 없을 때는 null 반환
        }
    }
    public PostDto approvePostFalse(Long id) {
        PostDto postDto = findPostDtoById(id);
        if (postDto != null) {
            postDto.setApprovedFalse();
            postDto.setMessage("게시글이 거부되었습니다.");
            return postDto;
        } else {
            return null; // 게시글을 찾을 수 없을 때는 null 반환
        }
    }

    private PostDto findPostDtoById(Long id) {
        return postRepository.findById(id)
                .map(PostDto::new)
                .orElse(null);
    }
}