package likelion.underdog.songgotmae.web;

import jakarta.persistence.EntityNotFoundException;
import likelion.underdog.songgotmae.domain.admin.AdminService;
import likelion.underdog.songgotmae.domain.post.Post;
import likelion.underdog.songgotmae.domain.post.PostRepository;
import likelion.underdog.songgotmae.web.dto.PostDto;
import lombok.Lombok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    public final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Autowired
    private AdminService adminService;

    @PatchMapping("{postId}")
    public PostDto.UpdateResponseDto updatePostStatus(
            @PathVariable Long postId,
            @RequestBody Map<String, Boolean> request,
            Principal principal) throws AccessDeniedException {

        String username = principal.getName();

        if (!adminService.isAdmin(username)) {
            throw new AccessDeniedException("Access denied");
        }

        if (!request.containsKey("approved")) {
            throw new IllegalArgumentException("not provided");
        }

        Boolean newApprovedStatus = request.get("approved");

        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isEmpty()) {
            throw new EntityNotFoundException("post not found.");
        }

        Post post = optionalPost.get();
        post.updateApprovedStatus(newApprovedStatus);
        postRepository.save(post);

        return new PostDto.UpdateResponseDto("Post status updated");
    }

}
