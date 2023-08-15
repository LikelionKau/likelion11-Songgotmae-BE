package likelion.underdog.songgotmae.web.dto;

import lombok.Builder;
import lombok.Data;

public class PostDto {

    @Data
    public static class CreateRequestDto {
        private String title;
        private String content;
    }



    @Data
    public static class ResponseDto {
        private Long postId;
        private String message;

        @Builder
        public ResponseDto(Long postId, String message) {
            this.postId = postId;
            this.message = message;
        }
    }


}
