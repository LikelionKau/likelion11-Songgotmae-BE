package likelion.underdog.songgotmae.web.dto;


import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public  class AgreementDto {

    @Data
    public static class Create {
        private final Long memberId;
        private Long postId;
        private Boolean isSupport;

    }
    @Data
    @AllArgsConstructor
    public static class Response {
        private String message;

    }
}
