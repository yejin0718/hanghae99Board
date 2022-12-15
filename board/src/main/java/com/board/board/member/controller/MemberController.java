package com.board.board.member.controller;


import com.board.board.global.ResponseMessage;
import com.board.board.global.jwt.JwtUtil;
import com.board.board.member.dto.MemberRequestDto;
import com.board.board.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage> login(@RequestBody MemberRequestDto memberRequestDto, HttpServletResponse response){
        String token = memberService.login(memberRequestDto);
        ResponseMessage responseMessage = new ResponseMessage("로그인 성공", 200, token);

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseMessage> signup(@RequestBody @Valid MemberRequestDto memberRequestDto, Errors errors){
        if(errors.hasErrors()){
            Map<String, String> validatorResult = memberService.validateHandling(errors);
            StringBuilder temp = new StringBuilder();
            for (String key : validatorResult.keySet()) {
                temp.append(key + " " + validatorResult.get(key) + "\n");
            }
            throw new IllegalArgumentException(temp.toString());
        }
        memberService.signup(memberRequestDto);
        ResponseMessage responseMessage = new ResponseMessage("회원가입 성공", 200, null);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

}
