package likelion.underdog.songgotmae.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.underdog.songgotmae.domain.member.service.memberService;
import likelion.underdog.songgotmae.web.dto.member.MemberJoinRequestDto;
import likelion.underdog.songgotmae.web.dto.member.MemberPwChangeRequestDto;
import likelion.underdog.songgotmae.web.dto.member.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "MemberJoin API", description = "회원가입 관련 API입니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final memberService memberService;

    @Operation(summary = "회원가입 - 테스트 완료 (황제철)")
    @PostMapping("/join")
    public ResponseEntity<?> joinMember(@RequestBody @Valid MemberJoinRequestDto joinRequest, BindingResult bindingResult) {
        MemberResponseDto.JoinResponseDto responseDto = memberService.joinMember(joinRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @Operation(summary = "관리자 계정 생성 API (임시) - 테스트 완료 (황제철)")
    @PostMapping("/join/admin")
    public ResponseEntity<?> joinAdmin(@RequestBody @Valid MemberJoinRequestDto joinRequest, BindingResult bindingResult) {
        MemberResponseDto.JoinResponseDto responseDto = memberService.joinAdminMember(joinRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @Operation(summary = "패스워드 찾기")
    @PutMapping("/{email}/password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid MemberPwChangeRequestDto pwChangeRequestDto, @PathVariable String email, BindingResult bindingResult) {
        MemberResponseDto.CommonResponseDto responseDto = memberService.changeMemberPassword(pwChangeRequestDto, email);
        return ResponseEntity
                .ok(responseDto);
    }

}
