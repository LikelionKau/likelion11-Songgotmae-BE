package likelion.underdog.songgotmae.util.constant;
public interface JwtVO {
    // TODO : refreshToken, OIDC 구현 보류
    public static final String SECRET_KEY = "임시언더독"; // 임시키
    public static final int EXPIRATION_TIME = 1000 * 60 * 60 * 2; // 엑세스 토큰 유효시간 : 2시간
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
}
