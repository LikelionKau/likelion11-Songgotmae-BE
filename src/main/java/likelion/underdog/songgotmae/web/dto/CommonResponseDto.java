package likelion.underdog.songgotmae.web.dto;
public class CommonResponseDto {
    private Long id;
    private String response;
    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}