package likelion.underdog.songgotmae.domain.agreement;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.member.repository.MemberRepository;
import likelion.underdog.songgotmae.domain.post.Post;
import likelion.underdog.songgotmae.domain.post.PostRepository;
import likelion.underdog.songgotmae.util.exception.CustomNotFoundException;
import likelion.underdog.songgotmae.web.dto.AgreementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;


    @Override
    public AgreementDto.Response createAgreement(Long postId, AgreementDto.Create request) {
        Long memberId = request.getMemberId();
        Boolean isAgree = request.getIsAgree();
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new CustomNotFoundException("회원을 찾을 수 없습니다."));
        Post findPost = postRepository.findById(postId).orElseThrow(() -> new CustomNotFoundException("게시글을 찾을 수 없습니다."));

        Optional<Agreement> existingAgreement = agreementRepository.findByMemberAndPost(findMember, findPost);
        existingAgreement.ifPresent(agreement -> agreementRepository.delete(agreement));

        Agreement newAgreement = Agreement.builder()
                .post(findPost)
                .member(findMember)
                .isAgree(request.getIsAgree())
                .build();
        Agreement saveAgreement = agreementRepository.save(newAgreement);

        long agreementCount = agreementRepository.countByPostAndIsAgree(findPost, true);
        long disagreementCount = agreementRepository.countByPostAndIsAgree(findPost, false);


        return AgreementDto.Response.builder()
                .agreementId(saveAgreement.getId())
                .message("게시글에 대한 반응이 정상적으로 반영되었습니다.")
                .build();
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
