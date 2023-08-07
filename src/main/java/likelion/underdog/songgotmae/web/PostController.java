package likelion.underdog.songgotmae.web;

import likelion.underdog.songgotmae.domain.post.Post;
import likelion.underdog.songgotmae.web.dto.CommonResponseDto;
import likelion.underdog.songgotmae.web.dto.PostDto;
import org.springframework.web.bind.annotation.*;
import likelion.underdog.songgotmae.domain.post.PostService;

@RestController
@RequestMapping("/board")
public class PostController {
    private String response;
    private PostService postService;

    @PostMapping("/write")
    public CommonResponseDto createPost(PostDto postDto) {
        Post post = Post.from(postDto);
        postService.newPost(post);

        CommonResponseDto responseDto = new CommonResponseDto();
        responseDto.setResponse("게시글이 작성되었습니다.");

        return responseDto; // 컨트롤러에서는 dto를 반환하고
    }
}
