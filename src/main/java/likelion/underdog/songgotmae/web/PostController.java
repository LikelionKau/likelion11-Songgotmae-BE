package likelion.underdog.songgotmae.web;

import likelion.underdog.songgotmae.domain.post.PostServiceImpl;
import likelion.underdog.songgotmae.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostServiceImpl postServiceImpl;

    @PatchMapping("/{postId}/approve")
    public PostDto.UpdateResponseDto approvePost(@PathVariable Long postId) {
        return postServiceImpl.approvePostTrue(postId);
    }

    @PatchMapping("/{postId}/disapprove")
    public PostDto.UpdateResponseDto disapprovePost(@PathVariable Long postId) {
        return postServiceImpl.approvePostFalse(postId);
    }


}
