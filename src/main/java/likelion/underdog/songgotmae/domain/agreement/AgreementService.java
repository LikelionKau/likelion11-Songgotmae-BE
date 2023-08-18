package likelion.underdog.songgotmae.domain.agreement;

import likelion.underdog.songgotmae.web.dto.AgreementDto;
import org.springframework.transaction.annotation.Transactional;

public interface AgreementService {

    void saveAgreement(Agreement agreement);

    AgreementDto createPostAgreement(Long id);

    AgreementDto createPostDisAgreement(Long id);

    AgreementDto.Response createAgreement(Long postId, AgreementDto.Create request);

    @Transactional(readOnly = true)
    AgreementDto.Response getAgreementById(Long agreementId);

}