package com.demoproject.demo.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Swagger가 적용될 Model에도 DTO에 대한 정보를 적용해줄 수 있다.
 * 이 응답은 다른 모든 응답이 상속받아서 갖도록 한다.
 */
@Getter @Setter
@ApiModel(value = "공통 응답 모델", description = "전달될 데이터와 별개로 API의 처리여부, 상태, 메시지가 담긴 데이터")   // Model에 대한 설명, 상세 설명
public class CommonResult {
    @ApiModelProperty(value = "응답 성공 여부: T/F")
    private boolean success;

    @ApiModelProperty(value = "응답 코드: >= 0 정상, < 0 비정상")
    private int code;

    @ApiModelProperty(value = "응답 메시지")
    private String message;
}
