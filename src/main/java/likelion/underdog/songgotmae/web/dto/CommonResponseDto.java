package likelion.underdog.songgotmae.web.dto;
public class CommonResponseDto {
    private Long id;
    private String response;

    // 기본 생성자와 게터, 세터 메서드 생략 (필요 시 추가 가능)

    // response 필드를 가져오는 게터 메서드
    public String getResponse() {
        return response;
    }

    // response 필드를 설정하는 세터 메서드
    public void setResponse(String response) {
        this.response = response;
    }

    public Long getId() {
        return id;
    }

    // response 필드를 설정하는 세터 메서드
    public void setId(Long id) {
        this.id = id;
    }
}