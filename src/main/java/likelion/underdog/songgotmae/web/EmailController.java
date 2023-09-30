//package likelion.underdog.songgotmae.web;
//
//import likelion.underdog.songgotmae.domain.member.service.EmailService;
//import likelion.underdog.songgotmae.web.dto.EmailRequestDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.mail.MessagingException;
//import java.security.NoSuchAlgorithmException;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("email")
//public class EmailController {
//
//    private final EmailService emailService;
//
//    @GetMapping("authcode")
//    public ResponseEntity<String> sendEmailPath(@RequestParam String emailAddress) throws MessagingException {
//        emailService.sendEmail(emailAddress);
//        return ResponseEntity.ok("이메일을 확인하세요");
//    }
//
//    @PostMapping("authcode")
//    public ResponseEntity<String> sendEmailAndCode(@RequestParam String emailAddress, @RequestBody EmailRequestDto dto) throws NoSuchAlgorithmException {
//        if (emailService.verifyEmailCode(emailAddress, dto.getCode())) {
//            return ResponseEntity.ok(emailService.makeMemberId(emailAddress));
//        }
//        return ResponseEntity.notFound().build();
//    }
//}
