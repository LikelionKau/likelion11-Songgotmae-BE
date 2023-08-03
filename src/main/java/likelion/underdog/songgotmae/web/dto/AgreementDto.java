package likelion.underdog.songgotmae.web.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AgreementDto {

    public static String Response;
    private Long memberId;
    private Long postId;

    @Data
    public class Create {
        private Boolean isSupport;

        public Long getPostId() {
            return postId;
        }

        public Long getMemberId() {
            return memberId;
        }
        public Boolean getIsSupport() {return isSupport;}
    }
    @Data
    public static class Response {
        private String message;

        public Response(String message) {

            this.message = message;
        }
    }

}
