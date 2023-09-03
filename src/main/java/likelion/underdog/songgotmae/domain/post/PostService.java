package likelion.underdog.songgotmae.domain.post;

import likelion.underdog.songgotmae.web.dto.PostDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    PostDto.SaveResponseDto createPost(PostDto.CreateRequestDto requestBody);
    PostDto.SaveResponseDto approvePostTrue(Long id);
    PostDto.SaveResponseDto approvePostFalse(Long id);
    List<PostDto.FindResponseDto> findAllPosts();
    List<PostDto.FindResponseDto> findApprovedPosts();
    List<PostDto.FindResponseDto> findMemberPosts(Long memberId);

    Page<Post> searchPost(PostDto.PostSearchRequestDto requestDto);
}
