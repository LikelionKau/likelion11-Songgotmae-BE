package likelion.underdog.songgotmae.domain.voc;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.member.repository.MemberRepository;
import likelion.underdog.songgotmae.domain.post.Post;
import likelion.underdog.songgotmae.util.exception.CustomNotFoundException;
import likelion.underdog.songgotmae.web.dto.PostDto;
import likelion.underdog.songgotmae.web.dto.VocDto;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

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

    @Override
    @Transactional
    public VocDto.DeleteResponseDto deletePost(Long postId) {
        Optional<Voc> optionalVoc = vocRepository.findById(postId);

        if (optionalVoc.isPresent()) {
            vocRepository.deleteById(postId);

            return VocDto.DeleteResponseDto.builder()
                    .deletedVocId(postId)
                    .message("게시물이 삭제되었습니다.")
                    .build();
        } else {
            throw new CustomNotFoundException("삭제할 게시물을 찾을 수 없습니다.");
        }
    }
    
    @Override
    public Page<VocDto.FindResponseDto> findAllVocsOrderByCreatedAt(Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Order.desc("createdAt")));

        Page<Voc> vocs = vocRepository.findAllByOrderByCreatedAt(sortedPageable);
        return vocs.map(v -> VocDto.FindResponseDto.builder().voc(v).build());
    }
}