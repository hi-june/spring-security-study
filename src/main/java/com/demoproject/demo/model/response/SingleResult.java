package com.demoproject.demo.model.response;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel(value = "단일 응답 모델", description = "API 반환값이 단일 객체일 경우 해당 모델로 처리합니다.")
public class SingleResult<T> extends CommonResult{  // Generic type T로 여러 엔티티에 적용가능하도록 만들어줌
    private T data;
}
