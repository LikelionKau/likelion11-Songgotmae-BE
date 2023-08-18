package likelion.underdog.songgotmae.web.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public record CommonResponseDto<T>(Integer code, String message, T data) {
}
