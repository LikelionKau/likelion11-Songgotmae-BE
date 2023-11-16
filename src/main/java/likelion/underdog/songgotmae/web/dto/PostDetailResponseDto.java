package likelion.underdog.songgotmae.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import likelion.underdog.songgotmae.domain.agreement.Agreement;
import likelion.underdog.songgotmae.domain.post.Post;

public record PostDetailResponseDto(

        @Schema(description = "게시글 id", example = "1")
        Long id,
        @Schema(description = "작성자 이름", example = "바코")
        String authorNickname,
        @Schema(description = "게시글 제목", example = "멋사 파이팅")
        String title,
        @Schema(description = "게시글 내용", example = "멋사 모두 파이팅")
        String content,
        @Schema(description = "찬성 수", example = "18")
        Long agreementCount,
        @Schema(description = "반대 수", example = "0")
        Long disagreementCount,
        @Schema(description = "찬반 총 개수", example = "18")
        Long totalOpinionCount
) {
    public PostDetailResponseDto(Post post, Long agreementCount, Long disagreementCount) {
        this(post.getId(), post.getAuthor().getNickname(),
                post.getTitle(), post.getContent(),
                agreementCount, disagreementCount,
                post.getTotalOpinionCount());
    }

}
