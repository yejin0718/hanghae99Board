package com.board.board.member.service;


import com.board.board.global.config.UserRoleEnum;
import com.board.board.member.dto.MemberRequestDto;
import com.board.board.member.entity.Member;
import com.board.board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    private final MemberRepository memberRepository;
    public String login(MemberRequestDto memberRequestDto) {

        String username = memberRequestDto.getUsername();
        String password = memberRequestDto.getPassword();

        Member member = memberRepository.findByUsername(username).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 아이디입니다.")
        );

        if(!member.getPassword().equals(password)){
            throw new IllegalArgumentException("틀린 비밀번호 입니다.");
        }

        return member.getUsername();

    }

    public String signup(MemberRequestDto memberRequestDto) {
        String username = memberRequestDto.getUsername();
        String password = memberRequestDto.getPassword();

        if(memberRepository.existsByUsername(username)){
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        UserRoleEnum role = UserRoleEnum.USER;
        if(memberRequestDto.isAdmin()){
            if(ADMIN_TOKEN.equals(memberRequestDto.getAdminCode())){
                role = UserRoleEnum.ADMIN;
            }else{
                throw new IllegalArgumentException("인증 코드가 올바르지 않습니다.");
            }
        }
        Member member = new Member(username, password, role);

        Member memberTemp = memberRepository.save(member);

        return memberTemp.getUsername();
    }
}
