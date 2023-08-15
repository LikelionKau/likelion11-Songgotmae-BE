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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Admin API", description = "어드민 페이지 관련 API입니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/v1")
public class AdminController {
    private final PostServiceImpl postService;

    @Operation(summary = "(관리자) 포스트 게시 허용")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "게시 허용 완료"),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST"),
            @ApiResponse(responseCode = "500", description = "Internal_Serer_Error")
    })
    @PatchMapping("/posts/{postId}/approve")
    public ResponseEntity<?> approvePost(@PathVariable Long postId) {

        PostDto.ResponseDto responseDto = postService.approvePostTrue(postId);
        CommonResponseDto<PostDto.ResponseDto> response = new CommonResponseDto<>(1, "게시글 찬성 성공", responseDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "(관리자) 포스트 게시 불허")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "게시 불허 완료"),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST"),
            @ApiResponse(responseCode = "500", description = "Internal_Serer_Error")
    })
    @PatchMapping("/posts/{postId}/disapprove")
    public ResponseEntity<?> disapprovePost(@PathVariable Long postId) {
        PostDto.ResponseDto responseDto = postService.approvePostFalse(postId);
        CommonResponseDto<PostDto.ResponseDto> response = new CommonResponseDto<>(1, "게시글 찬성 성공", responseDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


}
