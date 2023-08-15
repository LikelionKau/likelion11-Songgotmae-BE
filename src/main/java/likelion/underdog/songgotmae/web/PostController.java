package likelion.underdog.songgotmae.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.underdog.songgotmae.domain.post.PostServiceImpl;
import likelion.underdog.songgotmae.web.dto.CommonResponseDto;
import likelion.underdog.songgotmae.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Post API", description = "게시글 관련 API입니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostController {
    private final PostServiceImpl postService;

    @Operation(summary = "게시글 작성 api 입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "게시글 작성 완료"),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST"),
            @ApiResponse(responseCode = "500", description = "Internal_Serer_Error")
    })
    @PostMapping("/posts/new/{userId}")
    public ResponseEntity<?> createPost(@PathVariable Long userId, @RequestBody PostDto.CreateRequestDto requestBody) {
        PostDto.ResponseDto responseDto = postService.createPost(userId, requestBody);
        CommonResponseDto<PostDto.ResponseDto> response = new CommonResponseDto<>(1, "게시글 작성 성공", responseDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

}
