package likelion.underdog.songgotmae.domain.post;

import likelion.underdog.songgotmae.web.dto.PostDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostService {
    PostDto.SaveResponseDto createPost(PostDto.CreateRequestDto requestBody);

    PostDto.SaveResponseDto approvePostTrue(Long id);

    PostDto.SaveResponseDto approvePostFalse(Long id);

    @Transactional(readOnly = true)
    List<PostDto.FindResponseDto> findAllPosts();

    @Transactional(readOnly = true)
    List<PostDto.FindResponseDto> findApprovedPosts();

    @Transactional(readOnly = true)
    List<PostDto.FindResponseDto> findMemberPosts(Long memberId);

    @Transactional(readOnly = true)
    Post findById(Long postId);
}