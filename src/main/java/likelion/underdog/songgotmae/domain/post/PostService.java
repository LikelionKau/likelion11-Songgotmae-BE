package likelion.underdog.songgotmae.domain.post;

import org.springframework.stereotype.Service;
import likelion.underdog.songgotmae.web.dto.CommonResoponseDto;


@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public CommonResoponseDto newPost(Post postDto) {
        Post post = Post.from(postDto);
        postRepository.save(post); //여기서는 id를 반환한다.
        return CommonResoponseDto.id;
        //객체로 반환하라고 했는데 객체의 속성으로 반환해도 되는지 모르겠습니다.
    }
}