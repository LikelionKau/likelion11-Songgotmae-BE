package likelion.underdog.songgotmae.web.dto;

import likelion.underdog.songgotmae.domain.agreement.Agreement;
import likelion.underdog.songgotmae.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
        this.approved = agreement.getIsSupport();
        this.message = null;
    }

    public void setMessage(String message) {
        this.message = message;
    }

            @Data
           public static class Create {
                @NotNull
                private Long memberId;
                @NotNull
                private Boolean isSupport;

                public boolean isSupport() {
                    return isSupport;
                }
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
    }
}