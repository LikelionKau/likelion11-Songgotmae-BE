package likelion.underdog.songgotmae.domain.voc;

import likelion.underdog.songgotmae.web.dto.PostDto;
import likelion.underdog.songgotmae.web.dto.VocDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VocService {
    @Transactional
    VocDto.SaveResponseDto createPost(VocDto.CreateRequestDto requestBody);
    Page<PostDto.FindResponseDto> findAllVocsOrderByCreatedAt(Pageable pageable);
}

