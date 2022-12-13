package com.board.board.member.entity;


import com.board.board.global.config.UserRoleEnum;
import com.board.board.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    //이후 연관관계 시 사용
//    @OneToMany
//    private ArrayList<Post> postList;
//
//    @OneToMany
//    private ArrayList<Comment> commentList;

    public Member(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
