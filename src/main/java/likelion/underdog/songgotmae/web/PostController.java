package likelion.underdog.songgotmae.web;


import likelion.underdog.songgotmae.domain.post.PostRepository;
import likelion.underdog.songgotmae.domain.post.PostService;
import likelion.underdog.songgotmae.web.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    public final PostRepository postRepository;
    public PostService postService;

    @Autowired
    public PostController(PostService postService, PostRepository postRepository) {
        this.postRepository = postRepository;
        this.postService = postService;
    }


    @PatchMapping("{postId}")
    public PostDto.UpdateResponseDto updatePostStatus(
            @PathVariable Long postId,
            @RequestBody PostDto.newApprovedStatus newApprovedStatus) {

        PostDto.UpdateResponseDto updateResponseDto = postService.updatePostApprovedStatus(postId, newApprovedStatus);

        return updateResponseDto;
    }

}
