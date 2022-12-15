package com.board.board.global;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel(value = "응답 메시지 정보")
public class ResponseMessage {
    @ApiModelProperty(value = "응답 메시지", dataType = "String", example = "로그인 성공, 회원가입 성공, ...")
    private String message;

    @ApiModelProperty(value = "상태 코드", dataType = "int", example = "200")
    private int statusCode;

    /* Dto 데이터 추가 */
    @ApiModelProperty(value = "해당 요청에 대한 응답 데이터", dataType = "Object", example = "null or 해당 요청 데이터")
    private Object data;
}
