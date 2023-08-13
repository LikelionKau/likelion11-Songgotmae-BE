//package likelion.underdog.songgotmae.domain.member.repository;
//
//import likelion.underdog.songgotmae.domain.member.Member;
//import likelion.underdog.songgotmae.util.exception.CustomNotFoundException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class MemberRepoAdaptor {
//    private final MemberRepository memberRepository;
//
//    public Member save(Member member) {
//        return memberRepository.save(member);
//    }
//
//    public Member findById(Long id) {
//        return memberRepository.findById(id)
//                .orElseThrow(() -> new CustomNotFoundException("회원을 찾을 수 없습니다."));
//    }
//
//    public Member findByKauEmail(String kauEmail) {
//        return memberRepository.findByKauEmail(kauEmail)
//                .orElseThrow(() -> new CustomNotFoundException("회원을 찾을 수 없습니다."));
//    }
//
//    public Member findByNickname(String nickname) {
//        return memberRepository.findByNickname(nickname)
//                .orElseThrow(() -> new CustomNotFoundException("회원을 찾을 수 없습니다."));
//    }
//
//    public void delete(Member member) {
//        memberRepository.delete(member);
//    }
//}
