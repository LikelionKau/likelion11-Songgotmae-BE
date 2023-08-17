package likelion.underdog.songgotmae.domain.agreement;

import org.springframework.stereotype.Service;

@Service
public class AgreementService {
    private final AgreementRepository agreementRepository;

    public AgreementService(AgreementRepository agreementRepository) {
        this.agreementRepository = agreementRepository;
    }

    public void saveAgreement(Agreement agreement) {
        agreementRepository.save(agreement);
    }
}
