package likelion.underdog.songgotmae.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import likelion.underdog.songgotmae.domain.agreement.AgreementService;
import likelion.underdog.songgotmae.web.dto.AgreementDto;
import likelion.underdog.songgotmae.web.dto.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AgreementController {
    private final AgreementService agreementService;

    @Operation(summary = "게시글 찬성/반대 - 테스트 완료(황제철)")
    @PostMapping("/agreements/new/{postId}")
    public ResponseEntity<?> create(@PathVariable Long postId, @RequestBody @Valid AgreementDto.Create request, BindingResult bindingResult) {
        AgreementDto.Response responseDto = agreementService.createAgreement(postId, request);
        CommonResponseDto<AgreementDto.Response> response = new CommonResponseDto<>(1, "게시글에 대한 반응(찬성/반대) 생성이 완료되었습니다.", responseDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
