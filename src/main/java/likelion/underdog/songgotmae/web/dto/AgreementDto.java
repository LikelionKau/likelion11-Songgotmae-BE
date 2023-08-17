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
        this.member = agreement.getMember(); // 수정: Agreement 객체의 Member를 가져옴
        this.postId = agreement.getPost().getId();
        this.title = agreement.getPost().getTitle();
        this.content = agreement.getPost().getContent();
        this.approved = agreement.getIsSupport(); // 수정: Agreement에서 isSupport 가져옴
        this.message = null;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Member getAuthor() {return author;}

    public Boolean getApproved() {
        return approved;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
