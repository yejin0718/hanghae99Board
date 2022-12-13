package com.board.board.member.entity;


import com.board.board.global.config.UserRoleEnum;
import com.board.board.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@NoArgsConstructor
@Entity
@Setter
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
    @OneToMany
    private ArrayList<Post> postList = new ArrayList<>();

    public void addPostList(Post post){
        this.postList.add(post);
//        post.setMember(this);
    }
//
//    @OneToMany
//    private ArrayList<Comment> commentList;

    public Member(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
