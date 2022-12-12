package com.board.board.member.controller;


import com.board.board.member.dto.MemberRequestDto;
import com.board.board.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public String login(@RequestBody MemberRequestDto memberRequestDto){
        memberService.login(memberRequestDto.getUsername(), memberRequestDto.getPassword());
        return null;
    }
}
