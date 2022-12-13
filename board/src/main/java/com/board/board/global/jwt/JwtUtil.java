package com.board.board.global.jwt;



import com.board.board.global.config.UserRoleEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final UserDetailsService userDetailsService;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_KEY = "auth";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final long TOKEN_TIME = 60 * 60 * 1000L;

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //enum 자료형

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    // header 토큰을 가져오기
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        System.out.println("JwtUtil.resolveToken : " + bearerToken);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7); //연관되지 않은 값 삭제
        }
        return null;
    }

    // 토큰 생성
    public String createToken(String username, UserRoleEnum role) {
        Date date = new Date();
        System.out.printf("createToken : " +username);
        return BEARER_PREFIX + //토큰에 붙는 단순 문장
                Jwts.builder()
                        .setSubject(username)  //subject 공간에 username 집어넣기
                        .claim(AUTHORIZATION_KEY, role) //claim이라는 공간에 유저의 권한을 집어넣는데 이때 그 권한을 가져올 때는 AUTHORIZATION_KEY를 활용
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME)) //토큰의 유효기간 지정
                        .setIssuedAt(date)
                        .signWith(key, signatureAlgorithm) //토큰 암호화를 하는 부분, 키와 알고리즘을 매개변수로 전달
                        .compact();
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token); //검증과정에 필요한 key집어넣어야함, 목표 토큰을 parseClaimsJws에 집어넣어야함
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }

    // 토큰에서 사용자 정보 가져오기, 해당 부분은 위에서 토큰의 검증이 이루어 진 다음에 진행해야함
    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody(); //위와 거의 비슷하지만 getBody를 통해 내부 정보 추출
    }

    public Authentication createAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }


}