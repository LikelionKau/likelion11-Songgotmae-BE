package likelion.underdog.songgotmae.domain.agreement;

import likelion.underdog.songgotmae.web.dto.AgreementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface AgreementService {
     AgreementDto.Response submitAgreement(AgreementDto.Create agreementDto);

    AgreementDto.Response getAgreementById(Long agreementId);

}
