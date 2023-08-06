package likelion.underdog.songgotmae.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class OauthController {

    @ResponseBody
    @GetMapping("/oauth/kakao")
    public void kakaoCallback(@RequestParam String code) {
        log.info("code : " + code);
    }
}
