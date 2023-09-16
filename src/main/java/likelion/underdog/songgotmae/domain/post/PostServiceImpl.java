package likelion.underdog.songgotmae.domain.post;

import likelion.underdog.songgotmae.domain.agreement.Agreement;
import likelion.underdog.songgotmae.domain.agreement.AgreementRepository;
import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.member.repository.MemberRepository;
import likelion.underdog.songgotmae.util.exception.CustomNotFoundException;
import likelion.underdog.songgotmae.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    private final AgreementRepository agreementRepository;

    @Override
    public PostDto.SaveResponseDto createPost(PostDto.CreateRequestDto requestBody) {
        Optional<Member> optionalMember = memberRepository.findById(requestBody.getUserId());
        if (optionalMember.isPresent()) {
            Member findMember = optionalMember.get();
            Post newPost = Post.builder()
                    .author(findMember)
                    .title(requestBody.getTitle())
                    .content(requestBody.getContent())
                    .isApproved(false)
                    .build();
            Post savePost = postRepository.save(newPost);

            updateAgreementCountsForPost(savePost);

            return PostDto.SaveResponseDto.builder()
                    .postId(savePost.getId())
                    .message("게시글이 성공적으로 생성되었습니다.")
                    .build();
        } else {
            throw new CustomNotFoundException("작성자를 찾을 수 없습니다.");
        }
    }

    @Override
    public PostDto.SaveResponseDto approvePostTrue(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.updateApprovedTrue();
            Post savePost = postRepository.save(post);
            return PostDto.SaveResponseDto.builder()
                    .postId(savePost.getId())
                    .message("게시글을 허용 처리 하였습니다.")
                    .build();
        } else {
            throw new CustomNotFoundException("해당 게시글을 찾을 수 없습니다.");
        }
    }

    @Override
    public PostDto.SaveResponseDto approvePostFalse(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.updateApprovedFalse();
            Post savePost = postRepository.save(post);
            return PostDto.SaveResponseDto.builder()
                    .postId(savePost.getId())
                    .message("게시글을 불허 처리 하였습니다.")
                    .build();
        } else {
            throw new CustomNotFoundException("해당 게시글을 찾을 수 없습니다.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto.FindResponseDto> findAllPosts() {
        List<Post> findPosts = postRepository.findAllPosts();
        return getDtoList(findPosts);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto.FindResponseDto> findApprovedPosts() {
        List<Post> approvedPosts = postRepository.findApprovedPosts();
        return getDtoList(approvedPosts);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto.FindResponseDto> findMemberPosts(Long memberId) {
        List<Post> memberPosts = postRepository.findPostsByMemberId(memberId);
        return getDtoList(memberPosts);
    }

    @Override
    @Transactional(readOnly = true) // edited
    public Page<Post> searchPost(PostDto.PostSearchRequestDto requestDto) {
        Pageable pageable = PageRequest.of(requestDto.getPage(), requestDto.getSize());
        if (requestDto.getKeyword() == null || requestDto.getKeyword().isEmpty()) {
            return postRepository.findAll(pageable);
        } else {
            return postRepository.findByTitleContaining(requestDto.getKeyword(), pageable);
        }
    }
  
    @Override
    @Transactional(readOnly = true)
    public Page<PostDto.FindResponseDto> findAllPostsOrderByCreatedAt(Pageable pageable) {
        Page<Post> posts = postRepository.findAllByOrderByCreatedAt(pageable);
        return posts.map(p -> PostDto.FindResponseDto.builder().post(p).build());
    }

    /* ----- 반복 메서드 ----- */
    private void updateAgreementCountsForPost(Post post) {
        List<Agreement> agreements = agreementRepository.findByPost(post);

        long agreementCount = agreements.stream().filter(Agreement::getIsAgree).count();
        long disagreementCount = agreements.size() - agreementCount;

        post.updateAgreementCounts(agreementCount, disagreementCount);

    }

    private List<PostDto.FindResponseDto> getDtoList(List<Post> posts) {
        return posts.stream()
                .map(p -> PostDto.FindResponseDto.builder().post(p).build())
                .toList();
    }

}
