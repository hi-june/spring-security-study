package com.demoproject.demo.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Schema(title = "다중 응답 모델", description = "API 반환값이 단일 객체일 경우 해당 모델로 처리합니다.")
public class ListResult<T> extends CommonResult {
    private List<T> data;
}
