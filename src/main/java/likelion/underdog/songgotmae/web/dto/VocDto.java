package likelion.underdog.songgotmae.web.dto;

import likelion.underdog.songgotmae.domain.voc.Voc;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
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
    @Builder
    public static class SaveResponseDto {
        private Long vocId;
        private String message;
    }

    @Data
    public static class FindResponseDto {
        private Long vocId;
        private Long authorId;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        @Builder
        public FindResponseDto(Voc voc) {
            this.vocId = voc.getId();
            this.authorId = voc.getAuthor().getId();
            this.title = voc.getTitle();
            this.content = voc.getContent();
            this.createdAt = voc.getCreatedAt();
            this.modifiedAt = voc.getModifiedAt();
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

    @Data
    public static class UpdateRequestDto {
        @NotNull(message = "작성 유저가 있어야 합니다.")
        private Long vocId;
        @NotBlank(message = "제목을 입력해야 합니다.")
        private String title;
        @NotBlank(message = "내용을 입력해야 합니다.")
        private String content;
    }

}
