package likelion.underdog.songgotmae.web.dto;

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
    }
}
