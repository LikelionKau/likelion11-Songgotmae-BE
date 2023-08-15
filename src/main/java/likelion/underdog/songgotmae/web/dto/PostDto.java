package likelion.underdog.songgotmae.web.dto;

import lombok.Builder;
import lombok.Data;

public class PostDto {
    @Data
    public static class UpdateResponseDto {
        private Long postId;
        private String message;

        @Builder
        public UpdateResponseDto(Long postId, String message) {
            this.postId = postId;
            this.message = message;
        }
    }

    @Data
    public static class approvalStatusDto {
        private Boolean approved;
    }
}
