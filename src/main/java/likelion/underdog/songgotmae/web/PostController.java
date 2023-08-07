package likelion.underdog.songgotmae.web;

import likelion.underdog.songgotmae.domain.post.Post;
import likelion.underdog.songgotmae.web.dto.PostDto;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import likelion.underdog.songgotmae.domain.post.PostService;
@RestController
@RequestMapping("/board")
public class PostController {
    private String response;
    private PostService postService;

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String createPost(@RequestBody PostDto postDto) {
        Post post = Post.from(postDto);
        postService.newPost(post);
        response = "게시글이 작성되었습니다.";
        return response;
    }
    //* board/write경로에서 포스트 요청을 보낼시 작동하는 핸들러 메서드 createPost를 작성해 보았습니다.
    //*createPost는 사용자가 입력한 post에 관한 JSON데이터를 파라미터로 받은후 PostDto 객체로 저장합니다.
}
