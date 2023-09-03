package likelion.underdog.songgotmae.web.dto;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.post.Post;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class PostDto {

    @Data
    public static class CreateRequestDto {
        private Long userId;
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
    }

    @Data
    public static class ApproveRequestDto {
        private Long userId;
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

    @Data
    public static class PostSearchRequestDto {
        private String keyword;
        private int page;
        private int size;
    }

}
