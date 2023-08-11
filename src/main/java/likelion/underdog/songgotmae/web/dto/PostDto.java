package likelion.underdog.songgotmae.web.dto;

public class PostDto {
    public static class UpdateResponseDto {
        private String message;

        public UpdateResponseDto(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

    }
}
