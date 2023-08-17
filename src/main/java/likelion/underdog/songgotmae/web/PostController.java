package likelion.underdog.songgotmae.web;

import likelion.underdog.songgotmae.domain.agreement.Agreement;
import likelion.underdog.songgotmae.domain.agreement.AgreementService;
import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.post.Post;
import likelion.underdog.songgotmae.domain.post.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private final PostService postService;
    private final AgreementService agreementService;

    public PostController(PostService postService, AgreementService agreementService) {
        this.postService = postService;
        this.agreementService = agreementService;
    }

    @PostMapping("/post/create-agreement")
    public String createPostAgreement(@RequestParam Long postId, @RequestParam Long memberId) {
        Post post = postService.findById(postId);
        Member member = memberService.findById(memberId);

        Agreement agreement = new Agreement(member, post, true);
        agreementService.saveAgreement(agreement);

        return "Agreement created successfully";
    }
}