package site.metacoding.blogv1.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto<T> {
    private Integer code; // 실패 : -1. 성공 : 1
    private String msg;
    private T data;
}
