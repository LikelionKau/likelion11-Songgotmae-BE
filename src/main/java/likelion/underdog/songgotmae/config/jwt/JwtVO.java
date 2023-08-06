package likelion.underdog.songgotmae.config.jwt;

public interface JwtVO {
    /*
    * SECRET은 노출되면 안되므로 일반적으론 환경변수에 세팅 or AWS 꺼를 사용 (아래는 그냥 학습용)
    **/
    public static final String SECRET = "멋쟁이사자처럼언더독"; // HS256 (대칭키)
    public static final int EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7; // 엑세스 토큰 만료기간 : 7일
    public static final String TOKEN_PREFIX = "Bearer "; // JWT 프로토콜에 의거
    public static final String HEADER = "Authorization";
}
