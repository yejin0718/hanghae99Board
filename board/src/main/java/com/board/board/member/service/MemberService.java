package com.board.board.member.service;


import com.board.board.global.config.UserRoleEnum;
import com.board.board.global.jwt.JwtUtil;
import com.board.board.member.dto.MemberRequestDto;
import com.board.board.member.entity.Member;
import com.board.board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    private final MemberRepository memberRepository;

   @Transactional(readOnly = true)
    public String login(MemberRequestDto memberRequestDto) {

        String username = memberRequestDto.getUsername();
        String password = memberRequestDto.getPassword();
        Member member = memberRepository.findByUsername(username).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 아이디입니다.")
        );

        if(!passwordEncoder.matches(password, member.getPassword())){
            throw new IllegalArgumentException("틀린 비밀번호 입니다.");
        }


        return jwtUtil.createToken(member.getUsername(), member.getRole());

    }

    @Transactional
    public void signup(MemberRequestDto memberRequestDto) {
        String username = memberRequestDto.getUsername();
        String password = passwordEncoder.encode(memberRequestDto.getPassword());

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

    }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }
}
