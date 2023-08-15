package likelion.underdog.songgotmae.web;

import likelion.underdog.songgotmae.domain.agreement.AgreementService;
import likelion.underdog.songgotmae.web.dto.AgreementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AgreementController {
    private final AgreementService agreementService;

    @PostMapping("/agreements/new")
    public AgreementDto.Response create(@RequestBody AgreementDto.Create agreementDto) {
        AgreementDto.Response response = agreementService.submitAgreement(agreementDto);
        return response;
    }
}
