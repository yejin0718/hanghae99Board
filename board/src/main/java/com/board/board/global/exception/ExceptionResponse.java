package com.board.board.global.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private Date date;
    private String msg;

    /* 예외가 어디서 났는지 확인 */
    private String content;

}
