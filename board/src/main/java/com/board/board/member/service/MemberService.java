package com.board.board.member.service;


import com.board.board.global.jwt.JwtUtil;
import com.board.board.member.entity.Member;
import com.board.board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public String login(String username, String password) {

        Member member = memberRepository.findByUsername(username).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 아이디입니다.")
        );

        if(!member.getPassword().equals(password)){
            throw new IllegalArgumentException("틀린 비밀번호 입니다.");
        }

        return member.getUsername();

    }
}
