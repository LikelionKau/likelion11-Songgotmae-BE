package likelion.underdog.songgotmae.util.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CORS_PATTERNS {
    public static final List<String> ALLOW_ORIGINS = Arrays.asList(
//            "*"
            "http://songgotmae.s3-website.ap-northeast-2.amazonaws.com",
            "http://127.0.0.1:5500",
            "http://127.0.0.1:63342",
            "http://118.235.13.99:80"
            );
    public static final List<String> ALLOW_METHODS = Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH");
    public static final List<String> ALLOW_HEADERS = Arrays.asList("*");
    public static final List<String> EXPOSED_HEADERS = Arrays.asList("Authorization");
}
