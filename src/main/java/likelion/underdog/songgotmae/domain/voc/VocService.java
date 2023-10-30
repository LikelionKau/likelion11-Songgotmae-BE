package likelion.underdog.songgotmae.domain.voc;

import likelion.underdog.songgotmae.web.dto.VocDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VocService {
    @Transactional
    VocDto.SaveResponseDto createPost(VocDto.CreateRequestDto requestBody);
}

