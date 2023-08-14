package likelion.underdog.songgotmae.domain.post;

import likelion.underdog.songgotmae.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import likelion.underdog.songgotmae.web.dto.CommonResponseDto;

@Service
@RequiredArgsConstructor
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public CommonResponseDto newPost(PostDto postDto) {
        Post post = new Post();
        post.setUserId(postDto.getUserId());

        postRepository.save(post);

        CommonResponseDto responseDto = new CommonResponseDto();
        responseDto.setId(post.getId());

        return responseDto;
    }
}