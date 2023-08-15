package likelion.underdog.songgotmae.domain.post;

import likelion.underdog.songgotmae.util.exception.CustomNotFoundException;
import likelion.underdog.songgotmae.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostServiceImpl {
    private final PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }


    public PostDto.UpdateResponseDto approvePostTrue(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.updateApprovedTrue();
            return PostDto.UpdateResponseDto.builder()
                    .postId(post.getId())
                    .message("게시글을 허용 처리 하였습니다.")
                    .build();
        } else {
            throw new CustomNotFoundException("해당 게시글을 찾을 수 없습니다.");
        }
    }

    public PostDto.UpdateResponseDto approvePostFalse(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.updateApprovedFalse();
            return PostDto.UpdateResponseDto.builder()
                    .postId(post.getId())
                    .message("게시글을 허용 처리 하였습니다.")
                    .build();
        } else {
            throw new CustomNotFoundException("해당 게시글을 찾을 수 없습니다.");
        }
    }



}
