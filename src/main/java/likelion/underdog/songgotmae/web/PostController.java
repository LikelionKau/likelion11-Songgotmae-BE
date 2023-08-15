package likelion.underdog.songgotmae.web;

import likelion.underdog.songgotmae.web.dto.CommonResponseDto;
import likelion.underdog.songgotmae.web.dto.PostDto;
import org.springframework.web.bind.annotation.*;
import likelion.underdog.songgotmae.domain.post.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class PostController {
    private final PostService postService;

    @PostMapping("/write")
    public CommonResponseDto createPost(PostDto postDto) {
        CommonResponseDto responseDto = postService.newPost(postDto);
        responseDto.setResponse("게시글이 작성되었습니다.");
        return responseDto;
    }
}
