package likelion.underdog.songgotmae.web.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CommonResponseDto<T> {
    private final Integer code;
    private final String message;
    private final T data;
}
