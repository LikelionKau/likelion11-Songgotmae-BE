package likelion.underdog.songgotmae.web.dto;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.post.Post;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public class PostDto {

    @Data
    public static class CreateRequestDto {
        @NotBlank(message = "제목을 입력해야 합니다.")
        private String title;
        @NotBlank(message = "내용을 입력해야 합니다.")
        private String content;
    }

    @Data
    public static class ApproveRequestDto {
    }


    @Data
    public static class SaveResponseDto {
        private Long postId;
        private String message;

        @Builder
        public SaveResponseDto(Long postId, String message) {
            this.postId = postId;
            this.message = message;
        }
    }

    @Data
    public static class FindResponseDto {
        private Long postId;
        private Long authorId;
        private String title;
        private String content;
        private Boolean isApproved;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        @Builder
        public FindResponseDto(Post post) {
            this.postId = post.getId();
            this.authorId = post.getAuthor().getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.isApproved = post.getIsApproved();
            this.createdAt = post.getCreatedAt();
            this.modifiedAt = post.getModifiedAt();
        }
    }

    @Getter
    @NoArgsConstructor // for object mapper
    public static class PostSearchRequestDto {
        private String keyword;
        @Positive(message = "페이지 넘버는 양수여야 합니다.")
        private int page;
        @Positive(message = "페이지 크기는 양수여야 합니다.")
        private int size;




        @Builder
        public PostSearchRequestDto(String keyword, int page, int size) {
            this.keyword = keyword;
            this.page = page;
            this.size = size;
        }
    }
}
