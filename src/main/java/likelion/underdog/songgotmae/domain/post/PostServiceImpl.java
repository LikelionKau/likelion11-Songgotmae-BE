package likelion.underdog.songgotmae.domain.post;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.member.repository.MemberRepository;
import likelion.underdog.songgotmae.util.exception.CustomNotFoundException;
import likelion.underdog.songgotmae.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostServiceImpl {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public PostDto.ResponseDto createPost(Long userId, PostDto.CreateRequestDto requestBody) {
        Optional<Member> optionalMember = memberRepository.findById(userId);
        if (optionalMember.isPresent()) {
            Member findMember = optionalMember.get();
            Post newPost = Post.builder()
                    .author(findMember)
                    .title(requestBody.getTitle())
                    .content(requestBody.getContent())
                    .isApproved(false)
                    .build();
            return PostDto.ResponseDto.builder()
                    .postId(newPost.getId())
                    .message("게시글이 성공적으로 생성되었습니다.")
                    .build();
        } else {
            throw new CustomNotFoundException("작성자를 찾을 수 없습니다.");
        }
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }


    public PostDto.ResponseDto approvePostTrue(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.updateApprovedTrue();
            return PostDto.ResponseDto.builder()
                    .postId(post.getId())
                    .message("게시글을 허용 처리 하였습니다.")
                    .build();
        } else {
            throw new CustomNotFoundException("해당 게시글을 찾을 수 없습니다.");
        }
    }

    public PostDto.ResponseDto approvePostFalse(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.updateApprovedFalse();
            return PostDto.ResponseDto.builder()
                    .postId(post.getId())
                    .message("게시글을 허용 처리 하였습니다.")
                    .build();
        } else {
            throw new CustomNotFoundException("해당 게시글을 찾을 수 없습니다.");
        }
    }



}
