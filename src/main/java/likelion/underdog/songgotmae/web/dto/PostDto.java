package likelion.underdog.songgotmae.web.dto;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.post.Post;

public class PostDto {
    private Member author;
    private Long id;
    private String title;
    private String content;
    private Boolean approved;
    private String message;
    private Boolean isApproved;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.approved = post.getIsApproved();
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

    public Member getAuthor() {
        return author;
    }

    public Boolean getApproved() {
        return approved;
    }
    public void setApprovedTrue() {
        isApproved = true;
    }
    public void setApprovedFalse() {
        isApproved = false;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
