package com.board.board.global.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private Date date; //에러 날짜
    private String msg; //예외 메시지
    private String content; //예외가 어느 요청에서 났는지 확인

}
