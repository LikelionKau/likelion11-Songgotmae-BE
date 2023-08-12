package likelion.underdog.songgotmae.web;

import likelion.underdog.songgotmae.domain.post.Post;
import likelion.underdog.songgotmae.domain.post.PostService;
import likelion.underdog.songgotmae.web.dto.PostDto;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {
    private List<Post> posts;
    private final PostService postService;

    public PostController() {
        this.posts = new ArrayList<>();
        this.postService = new PostService();
    }

    @PostMapping
    public PostDto createPost(@RequestBody PostDto postDto) {
        Post post = new Post(postDto.getAuthor(), postDto.getTitle(), postDto.getContent());
        posts.add(post);
        return new PostDto(post);
    }

    @GetMapping("/{postId}")
    public PostDto getPostDtoById(@PathVariable Long postId) {
        return postService.getPostDtoById(postId);
    }

    @PatchMapping("/{postId}/approve")
    public String approvePost(@PathVariable Long postId) {
        return postService.approvePost(postId);
    }

    @PatchMapping("/{postId}/disapprove")
    public String disapprovePost(@PathVariable Long postId) {
        return postService.disapprovePost(postId);
    }
}