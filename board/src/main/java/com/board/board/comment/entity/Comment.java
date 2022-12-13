package com.board.board.comment.entity;

import com.board.board.global.Timestamped;
import com.board.board.member.entity.Member;
import com.board.board.post.entity.Post;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Comment extends Timestamped {
    @Id
    @GeneratedValue
    private Long id;


    @Column(nullable = false)
    private String reply;


    @ManyToOne(fetch=LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @ManyToOne(fetch=LAZY)
    @JoinColumn(name="post_id")
    private Post post;


    /* 연관관계 편의 메서드 */
    public void changeMember(Member member){
        this.member = member;
        member.addCommentList(this);
    }

    public void changePost(Post post){
        this.post=post;
        post.addCommentList(this);
    }

}
