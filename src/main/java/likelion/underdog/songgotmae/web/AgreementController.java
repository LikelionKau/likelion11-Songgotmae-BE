package likelion.underdog.songgotmae.web;

import likelion.underdog.songgotmae.domain.agreement.Agreement;
import likelion.underdog.songgotmae.domain.agreement.AgreementService;
import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.member.MemberService;
import likelion.underdog.songgotmae.domain.post.Post;
import likelion.underdog.songgotmae.domain.post.PostService;
import likelion.underdog.songgotmae.web.dto.AgreementDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agreements")
public class AgreementController {
    private final PostService postService;
    private final AgreementService agreementService;
    private final MemberService memberService;

    public AgreementController(PostService postService, AgreementService agreementService, MemberService memberService) {
        this.postService = postService;
        this.agreementService = agreementService;
        this.memberService = memberService;
    }

    @PostMapping("/create-agreement")
    public AgreementDto createPostAgreement(@RequestParam Long postId, @RequestParam Long memberId) {
        Post post = postService.findById(postId);
        Member member = memberService.findById(memberId);

        Agreement agreement = new Agreement(member, post, true);
        agreementService.saveAgreement(agreement);

        return new AgreementDto(agreement);
    }
}