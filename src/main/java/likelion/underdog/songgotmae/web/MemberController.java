package likelion.underdog.songgotmae.web;

import likelion.underdog.songgotmae.domain.member.service.MemberService;
import likelion.underdog.songgotmae.web.dto.CommonResponseDto;
import likelion.underdog.songgotmae.web.dto.member.MemberRequestDto;
import likelion.underdog.songgotmae.web.dto.member.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid MemberRequestDto.JoinRequestDto joinRequest, BindingResult bindingResult) {
        MemberResponseDto.JoinResponseDto joinResponse = memberService.joinMember(joinRequest);
        return new ResponseEntity<>(new CommonResponseDto<>(1, "회원가입 성공", joinResponse), HttpStatus.CREATED);
    }
}
