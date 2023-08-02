package likelion.underdog.songgotmae.domain.post;

import likelion.underdog.songgotmae.web.dto.PostDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class PostService {

    private String response = "게시글이 작성되었습니다.";

    @Autowired
    private PostRepository postRepository;

    public String newPost(PostDto postDto) {

        PostEntity postEntity = new PostEntity();
        postEntity.setNumber(postDto.getNumber());
        postEntity.setUserId(postDto.getUserId());
        postEntity.setTitle(postDto.getTitle());
        postEntity.setContents(postDto.getContents());
        postEntity.setClickNumber(postDto.getClickNumber());
        postEntity.setCreatedAt(postDto.getCreatedAt());
        postEntity.setModifiedAt(postDto.getModifiedAt());

        // 데이터베이스에 게시글 저장
        postRepository.save(postEntity);

        return response;
    }
}