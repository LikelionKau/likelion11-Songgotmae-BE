package likelion.underdog.songgotmae.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.Comment;

import javax.validation.constraints.NotNull;

public record PostDetailFindRequestDto(
        @NotNull(message = "postId가 필요합니다.")
        @Schema(description = "조회하려는 게시글 id", example = "1")
        Long postId
) {
    public PostDetailFindRequestDto(Long postId) {
        this.postId = postId;
    }

}
