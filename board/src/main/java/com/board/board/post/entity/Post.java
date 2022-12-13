package com.board.board.post.entity;

import com.board.board.global.Timestamped;
import com.board.board.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable =false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "member_id")
    private Member member;


    public Post(String title, String content, Member member){
        this.title = title;
        this.content = content;
//        this.member = member;
        changeMember(member);
    }

    public void changeMember(Member member){
        this.member = member;
        member.addPostList(this);
    }


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
