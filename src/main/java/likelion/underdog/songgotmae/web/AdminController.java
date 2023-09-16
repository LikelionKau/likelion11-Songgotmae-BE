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

@Tag(name = "Admin API", description = "어드민 페이지 관련 API입니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/v1")
public class AdminController {
    private final PostServiceImpl postService;

    @Operation(summary = "(관리자) 포스트 게시 허용 - 테스트 완료 (황제철)")
    @PatchMapping("/posts/{postId}/approve")
    public ResponseEntity<?> approvePost(@PathVariable Long postId) {

        PostDto.SaveResponseDto saveResponseDto = postService.approvePostTrue(postId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saveResponseDto);
    }

    @Operation(summary = "(관리자) 포스트 게시 불허 - 테스트 완료 (황제철)")
    @PatchMapping("/posts/{postId}/disapprove")
    public ResponseEntity<?> disapprovePost(@PathVariable Long postId) {
        PostDto.SaveResponseDto saveResponseDto = postService.approvePostFalse(postId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saveResponseDto);
    }


}
