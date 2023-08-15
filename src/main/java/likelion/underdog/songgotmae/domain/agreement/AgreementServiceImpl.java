package likelion.underdog.songgotmae.domain.agreement;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.member.repository.MemberRepository;
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
    public AgreementDto.Response submitAgreement(AgreementDto.Create request) {
        if (request.getIsAgree() == null) {
            AgreementDto.Response response = AgreementDto.Response.builder()
                    .msg("문제가 있다.")
                    .build();
            return response;
        }

        if (request.getIsAgree()) {
            Post post = postRepository.findById(request.getPostId()).orElseThrow(() -> new IllegalArgumentException("찾을 수 없음"));
            Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new IllegalArgumentException("찾을 수 없음"));

            Agreement saveAgreement = agreementRepository.save(new Agreement(member, post, true));

            AgreementDto.Response response = AgreementDto.Response.builder()
                    .agreementId(saveAgreement.getId())
                    .build();
            return response;
        } else {
            Post post = postRepository.findById(request.getPostId()).orElseThrow(() -> new IllegalArgumentException("찾을 수 없음"));
            Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new IllegalArgumentException("찾을 수 없음"));

            Agreement saveAgreement = agreementRepository.save(new Agreement(member, post, false));

            AgreementDto.Response response = AgreementDto.Response.builder()
                    .agreementId(saveAgreement.getId())
                    .build();
            return response;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public AgreementDto.Response getAgreementById(Long agreementId) {
        AgreementDto.Response response = AgreementDto.Response.builder()
                .agreementId(agreementId)
                .build();
        return response;
    }

}
