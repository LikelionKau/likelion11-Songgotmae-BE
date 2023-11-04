package likelion.underdog.songgotmae.web.dto;

import likelion.underdog.songgotmae.domain.voc.Voc;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VocDto {

    @Data
    public static class CreateRequestDto {
        private Long userId;
        private String title;
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
}