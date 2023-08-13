package likelion.underdog.songgotmae.util.formatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import likelion.underdog.songgotmae.web.dto.CommonResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

@Slf4j
public class CustomResponseFormatter {

    /*
     * 로그인 성공
     **/
    public static void success(HttpServletResponse response, Object dto) {
        try {
            ObjectMapper om = new ObjectMapper();
            CommonResponseDto responseDto = new CommonResponseDto(1, "로그인 성공", dto); // 401 인증안됨, 403 권한없음
            String responseBody = om.writeValueAsString(responseDto);
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(HttpStatus.OK.value()); // 401 인증안됨, 403 권한없음
            response.getWriter().println(responseBody); // dto로 반환 가능
        } catch (Exception e) {
            // TODO : handle exception
            log.error("서버 파싱 에러");
        }
    }

    /*
     * 실패 (통합)
     **/
    public static void fail(HttpServletResponse response, String message , Object dto, HttpStatus statusCode) {
        try {
            ObjectMapper om = new ObjectMapper();
            CommonResponseDto responseDto = new CommonResponseDto(-1, message, dto); // 401 인증안됨, 403 권한없음
            String responseBody = om.writeValueAsString(responseDto);
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(statusCode.value()); // 401 인증안됨, 403 권한없음
            response.getWriter().println(responseBody); // dto로 반환 가능
        } catch (Exception e) {
            // TODO : handle exception
            log.error("서버 파싱 에러");
        }
    }


}
