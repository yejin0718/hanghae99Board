package com.board.board.member.controller;


import com.board.board.global.security.MemberDetailsImpl;
import com.board.board.member.dto.MemberRequestDto;
import com.board.board.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public String login(@RequestBody MemberRequestDto memberRequestDto, HttpServletResponse response){
        return memberService.login(memberRequestDto, response);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody MemberRequestDto memberRequestDto){
        return memberService.signup(memberRequestDto);
    }

    @GetMapping("/test")
    public String test(@AuthenticationPrincipal MemberDetailsImpl userDetails){
        System.out.println(userDetails.getUsername());
        return "성공";
    }
}
