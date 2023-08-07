package likelion.underdog.songgotmae.domain.post;

import likelion.underdog.songgotmae.web.dto.PostDto;
import org.springframework.stereotype.Service;
import likelion.underdog.songgotmae.web.dto.CommonResponseDto;


@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public CommonResponseDto newPost(PostDto postDto) {
        Post post = new Post();
        post.setUserId(postDto.getUserId());
        // 필요한 다른 값을 post 객체에 설정해야 할 수도 있습니다.

        postRepository.save(post);

        // 저장한 post의 id 값을 가져와서 CommonResponseDto에 설정
        CommonResponseDto responseDto = new CommonResponseDto();
        responseDto.setId(post.getId());

        return responseDto; // 저장한 post의 id 값을 포함한 CommonResponseDto에 리턴
    }
}