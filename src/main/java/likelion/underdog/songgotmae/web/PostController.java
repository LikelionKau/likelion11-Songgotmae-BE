package likelion.underdog.songgotmae.web;

import likelion.underdog.songgotmae.domain.post.Post;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostDto createPost(@RequestBody PostDto postDto) {
        Post post = new Post(postDto.getAuthor(), postDto.getTitle(), postDto.getContent());
        return new PostDto(post);
    }

    @GetMapping("/{postId}")
    public PostDto getPostDtoById(@PathVariable Long postId) {
        return postService.getPostDtoById(postId);
    }

    @PatchMapping("/{postId}/approve")
    public PostDto approvePost(@PathVariable Long postId) {
        return postService.approvePostTrue(postId);
    }

    @PatchMapping("/{postId}/disapprove")
    public PostDto disapprovePost(@PathVariable Long postId) {
        return postService.approvePostFalse(postId);
    }
}
