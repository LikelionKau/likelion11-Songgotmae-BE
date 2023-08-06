package likelion.underdog.songgotmae.constant;

public class StaticValue {
    /* 에러 코드 static 값 */
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int METHOD_NOT_ALLOWED = 405;
    public static final int INTERNAL_SERVER_ERROR = 500;

    /* swagger urls */
    public static final String[] SwaggerUrlPatterns = {
//            "/swagger-resources",
//            "/swagger-resources/**",
//            "/configuration/ui",
//            "/configuration/security",
//            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
//            "/swagger-ui/index.html",
    };
}
