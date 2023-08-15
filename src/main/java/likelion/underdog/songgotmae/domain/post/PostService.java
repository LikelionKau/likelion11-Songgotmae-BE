package likelion.underdog.songgotmae.domain.post;

import jakarta.persistence.EntityNotFoundException;
import likelion.underdog.songgotmae.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    public final PostRepository postRepository;

    @Transactional
    public PostDto.UpdateResponseDto updatePostApprovedStatus(Long postId, PostDto.approvalStatusDto approvalStatusDto) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isEmpty()) {
            throw new EntityNotFoundException("post not found.");
        }

        Post post = optionalPost.get();
        post.updateApprovedTrue();
        postRepository.save(post);

        return PostDto.UpdateResponseDto.builder()
                .postId(postId)
                .message("updated")
                .build();
    }
}
