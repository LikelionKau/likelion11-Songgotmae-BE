package likelion.underdog.songgotmae.domain.agreement;

import likelion.underdog.songgotmae.web.dto.AgreementDto;

public interface AgreementService {
     AgreementDto.Response createAgreement(Long postId, AgreementDto.Create agreementDto);

    AgreementDto.Response getAgreementById(Long agreementId);

}
