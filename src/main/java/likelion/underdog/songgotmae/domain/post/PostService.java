package likelion.underdog.songgotmae.domain.post;

import org.springframework.stereotype.Service;
import likelion.underdog.songgotmae.web.dto.CommonResponseDto;


@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public CommonResponseDto newPost(Post postDto) {
        Post post = Post.from(postDto);
        postRepository.save(post); // postRepository.save(post)를 실행하여 post를 저장

        // 저장한 post의 id 값을 가져와서 CommonResponseDto에 설정
        CommonResponseDto responseDto = new CommonResponseDto();
        responseDto.setId(post.getId());

        return responseDto; // 저장한 post의 id 값을 포함한 CommonResponseDto에 리턴
    }
}