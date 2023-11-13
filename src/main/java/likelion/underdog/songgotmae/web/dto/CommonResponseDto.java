package likelion.underdog.songgotmae.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CommonResponseDto<T> {
    private final Integer code;
    private final String message;
    private T data;

}
