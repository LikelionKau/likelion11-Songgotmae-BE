package likelion.underdog.songgotmae.web.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AgreementDto {

    private Long memberId;
    private Long postId;

    @Data
    public static class Create {
        private Boolean isSupport;

        public Long getPostId() {
            return postId;
        }

        public Long getMemberId() {
            return memberId;
        }
    }
    @Data
    public static class Response {
        private String message;

        public Response(String message) {

            this.message = message;
        }
    }

}
