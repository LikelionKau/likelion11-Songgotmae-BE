package likelion.underdog.songgotmae.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private int number;
    private Long userId;
    private String title;
    private String contents;
    private int clickNumber;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
