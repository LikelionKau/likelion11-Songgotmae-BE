package likelion.underdog.songgotmae.domain.agreement;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.member.MemberRepository;
import likelion.underdog.songgotmae.domain.post.Post;
import likelion.underdog.songgotmae.domain.post.PostRepository;
import likelion.underdog.songgotmae.web.dto.AgreementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public void submitAgreement(AgreementDto.Create agreementDto) {
        if (Boolean.TRUE.equals(agreementDto.getIsSupport())) {
            Post post = postRepository.findById(agreementDto.getPostId()).orElseThrow(() -> new IllegalArgumentException("찾을 수 없음"));
            Member member = memberRepository.findById(agreementDto.getMemberId()).orElseThrow(() -> new IllegalArgumentException("찾을 수 없음"));

            Agreement agreement = new Agreement(member, post, true);
            agreementRepository.save(agreement);
        }
    }

    @Override
    @Transactional(readOnly = true  )
    public AgreementDto.Response getAgreementById(Long agreementId) {
        return new AgreementDto.Response("hello");
    }

}
