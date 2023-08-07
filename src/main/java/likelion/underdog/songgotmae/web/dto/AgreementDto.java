package likelion.underdog.songgotmae.web.dto;


import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AgreementDto {

    @Data
    public static class Create {
        private final Long memberId;
        private Long postId;
        private Boolean isAgree;
    }

    @Data
    public static class Response {
        private Long agreementId;
        private String msg;

        @Builder
        public Response(Long agreementId, String msg) {
            this.agreementId = agreementId;
            this.msg = msg;
        }

    }
}
