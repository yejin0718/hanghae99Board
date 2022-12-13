package com.board.board.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseMessage {
    private String message;
    private int statusCode;
    /* Dto 데이터 추가 */
    private Object data;
}
