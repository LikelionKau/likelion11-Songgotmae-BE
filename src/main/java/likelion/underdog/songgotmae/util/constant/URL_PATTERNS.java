package likelion.underdog.songgotmae.util.constant;

public class URL_PATTERNS {
    /* swagger urls */
    public static final String[] SWAGGER_URL_PATTERNS = {
//            "/swagger-resources",
//            "/swagger-resources/**",
//            "/configuration/ui",
//            "/configuration/security",
//            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
//            "/swagger-ui/index.html",
    };

    public static final String[] H2_URL_PATTERNS = {
            "/h2-console/**",
    };

    public static final String[] NEED_LOGIN_URL_PATTERNS = {
            "/api/v1/posts/**", // 포스팅 관련
    };

    public static final String[] ADMIN_PAGE_URL_PATTERNS = {
            "/admin/v1/**",
    };

}
