package com.board.board.member.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ApiModel(value = "회원가입 요청 정보")
public class MemberRequestDto {
    @ApiModelProperty(value = "유저 이름", dataType = "String", example = "아이디는 4~10자 영문 대 소문자, 숫자를 사용하세요.")
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{4,10}", message = "아이디는 4~10자 영문 대 소문자, 숫자를 사용하세요.")
    private String username;

    @ApiModelProperty(value = "유저 비밀번호", dataType = "String", example = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,15}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @ApiModelProperty(value = "관리자 여부 확인", dataType = "boolean", example = "true = 관리자, false = 유저")
    private boolean isAdmin;

    @ApiModelProperty(value = "관리자 암호", dataType = "String")
    private String adminCode = "";
}
