package likelion.underdog.songgotmae.domain.agreement;

import likelion.underdog.songgotmae.web.dto.AgreementDto;
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


    public AgreementDto createPostAgreement(Long id) {
        Agreement agreement = findAgreementById(id);
        if (agreement != null) {
            agreement.setIsSupport(true);
            AgreementDto agreementDto = new AgreementDto(agreement);
            agreementDto.setMessage("게시글에 찬성하였습니다.");
            return agreementDto;
        } else {
            return null; // 게시글을 찾을 수 없을 때는 null 반환
        }
    }

    public AgreementDto createPostDisAgreement(Long id) {
        Agreement agreement = findAgreementById(id);
        if (agreement != null) {
            agreement.setIsSupport(false);
            AgreementDto agreementDto = new AgreementDto(agreement);
            agreementDto.setMessage("게시글에 반대하였습니다.");
            return agreementDto;
        } else {
            return null; // 게시글을 찾을 수 없을 때는 null 반환
        }
    }

    private Agreement findAgreementById(Long id) {
        return agreementRepository.findById(id).orElse(null);
    }

}