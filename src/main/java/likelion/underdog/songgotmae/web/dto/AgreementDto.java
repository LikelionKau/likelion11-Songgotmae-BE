package likelion.underdog.songgotmae.web.dto;

<<<<<<< HEAD
import likelion.underdog.songgotmae.domain.agreement.Agreement;
import likelion.underdog.songgotmae.domain.member.Member;

public class AgreementDto {
    private Long id;
    private Member member; // 수정: AgreementDto에서 사용하는 Member 객체
    private Long postId;
    private String title;
    private String content;
    private Boolean approved;
    private String message;

    public AgreementDto(Agreement agreement) {
        this.id = agreement.getId();
        this.member = agreement.getMember();
        this.postId = agreement.getPost().getId();
        this.title = agreement.getPost().getTitle();
        this.content = agreement.getPost().getContent();
        this.approved = agreement.getIsSupport();
        this.message = null;
    }
    public void setMessage(String message) {
        this.message = message;
=======

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AgreementDto {

    @Data
    public static class Create {
        @NotNull
        private Long memberId;
        @NotNull
        private Boolean isAgree;
    }

    @Data
    public static class Response {
        private Long agreementId;
        private String message;

        @Builder
        public Response(Long agreementId, String message) {
            this.agreementId = agreementId;
            this.message = message;
        }

>>>>>>> dev
    }
}
