package likelion.underdog.songgotmae.web;

import likelion.underdog.songgotmae.domain.post.PostServiceImpl;
import likelion.underdog.songgotmae.web.dto.CommonResponseDto;
import likelion.underdog.songgotmae.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostController {
    private final PostServiceImpl postService;

    @PostMapping("/posts/new/{userId}")
    public ResponseEntity<?> createPost(@PathVariable Long userId, @RequestBody PostDto.CreateRequestDto requestBody) {
        PostDto.ResponseDto responseDto = postService.createPost(userId, requestBody);
        CommonResponseDto<PostDto.ResponseDto> response = new CommonResponseDto<>(1, "게시글 작성 성공", responseDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PatchMapping("/posts/{postId}/approve")
    public PostDto.ResponseDto approvePost(@PathVariable Long postId) {
        return postService.approvePostTrue(postId);
    }

    @PatchMapping("/posts/{postId}/disapprove")
    public PostDto.ResponseDto disapprovePost(@PathVariable Long postId) {
        return postService.approvePostFalse(postId);
    }


}
