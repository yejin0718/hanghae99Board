package com.board.board.post.entity;

import com.board.board.global.Timestamped;
import com.board.board.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
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
     @JoinColumn(name = "member_id")
     private Member member;

    /* 연관관계 편의 매서드 추가할 것 */



}
