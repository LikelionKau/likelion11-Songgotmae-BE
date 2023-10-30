package likelion.underdog.songgotmae.web.dto;

import likelion.underdog.songgotmae.domain.member.Member;
import likelion.underdog.songgotmae.domain.voc.Voc;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class VocDto {

    @Data
    public static class CreateRequestDto {
        @NotNull(message = "작성 유저가 있어야 합니다.")
        private Long userId;
        @NotBlank(message = "제목을 입력해야 합니다.")
        private String title;
        @NotBlank(message = "내용을 입력해야 합니다.")
        private String content;
    }



    @Data
    public static class SaveResponseDto {
        private Long vocId;
        private String message;

        @Builder
        public SaveResponseDto(Long vocId, String message) {
            this.vocId = vocId;
            this.message = message;
        }
    }


    @Data
    public static class DeleteResponseDto {
        private Long deletedVocId;
        private String message;

        @Builder
        public DeleteResponseDto(Long deletedVocId, String message) {
            this.deletedVocId = deletedVocId;
            this.message = "게시물을 삭제하였습니다.";
        }
    }
}
