package likelion.underdog.songgotmae.domain.member.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @DisplayName("메일 전송")
    @Test
    @WithMockUser
    public void sendMail() throws Exception {
        //given
        String address = "ghkdwp018@kau.kr";

        //when
        emailService.sendEmail(address);

        //then

    }

}
