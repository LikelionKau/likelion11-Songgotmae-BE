package likelion.underdog.songgotmae.util.formatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateFormatter {
    public static String toStringFormat(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // 이렇게 안하고 냅다 문자열로 날려주면 20220601Tfdjalflas 뭐 이런식으로 리턴됨
    }
}
