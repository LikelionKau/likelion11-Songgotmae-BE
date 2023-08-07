package likelion.underdog.songgotmae.web.dto;
import likelion.underdog.songgotmae.domain.post.Post;


public class CommonResoponseDto {
    private Long id;
    private String response;

    public void CommonResponseDto() {
        response = "게시글이 성공적으로 작성되었습니다.";
    }

    public Post getPostId(Post postId) {
        return postId;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //return값을 id값에 저장해야 되는데 이 코드가 맞는지는 잘 모르겠습니다.



}
