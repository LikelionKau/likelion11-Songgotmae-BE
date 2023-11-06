package likelion.underdog.songgotmae.domain.voc;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.member.repository.MemberRepository;
import likelion.underdog.songgotmae.util.exception.CustomNotFoundException;
import likelion.underdog.songgotmae.web.dto.VocDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VocServiceImpl implements VocService {
    private final MemberRepository memberRepository;
    private final VocRepository vocRepository;

    @Override
    @Transactional
    public VocDto.SaveResponseDto createPost(VocDto.CreateRequestDto requestBody) {
        Optional<Member> optionalMember = memberRepository.findById(requestBody.getUserId());
        if (optionalMember.isPresent()) {
            Member findMember = optionalMember.get();
            Voc newVoc = Voc.builder()
                    .id(findMember.getId())
                    .title(requestBody.getTitle())
                    .content(requestBody.getContent())
                    .build();
            Voc saveVoc = vocRepository.save(newVoc);


            return VocDto.SaveResponseDto.builder()
                    .vocId(saveVoc.getId())
                    .message("게시글이 성공적으로 생성되었습니다.")
                    .build();
        } else {
            throw new CustomNotFoundException("작성자를 찾을 수 없습니다.");
        }
    }

    @Override
    @Transactional
    public VocDto.SaveResponseDto updatePost(VocDto.UpdateRequestDto updateRequest) {
        Optional<Voc> optionalVoc = vocRepository.findById(updateRequest.getVocId());

        if (optionalVoc.isPresent()) {
            Voc existingVoc = optionalVoc.get();

            Voc updatedVoc = Voc.builder()
                    .id(existingVoc.getId())
                    .title(updateRequest.getTitle())
                    .content(updateRequest.getContent())
                    .build();

            updatedVoc = vocRepository.save(updatedVoc);

            return VocDto.SaveResponseDto.builder()
                    .vocId(updatedVoc.getId())
                    .message("Voc가 성공적으로 업데이트되었습니다.")
                    .build();
        } else {
            throw new CustomNotFoundException("사용자를 찾을 수 없습니다: " + updateRequest.getVocId());
        }
    }

}

