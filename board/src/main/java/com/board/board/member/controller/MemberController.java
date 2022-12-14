package com.board.board.member.controller;


import com.board.board.global.ResponseMessage;
import com.board.board.global.jwt.JwtUtil;
import com.board.board.global.security.MemberDetailsImpl;
import com.board.board.member.dto.MemberRequestDto;
import com.board.board.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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
//                model.addAttribute(key, validatorResult.get(key));
            }
            throw new IllegalArgumentException(temp.toString());
        }
        memberService.signup(memberRequestDto);
        ResponseMessage responseMessage = new ResponseMessage("회원가입 성공", 200, null);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(@AuthenticationPrincipal MemberDetailsImpl memberDetails){
        //위에 @AuthenticationPrincipal를 통해 해당 요청에 대해 Security를 거친다. 이때 Filter를 거치면서 나오는 memberDetails에 유저 정보가 들어가 있다.

        System.out.println(memberDetails.getMember());; //Member객체

        return "성공";
    }
    //현재 위 함수의 경우 현재 url 즉, "/member"이 모두 허용되어있기 때문에 로그인이 안되어도 접속이 가능해 오류가 난다.
    //다른곳에서 사용하면 괜찮다.
}
