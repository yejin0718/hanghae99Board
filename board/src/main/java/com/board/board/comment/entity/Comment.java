package com.board.board.comment.entity;

import com.board.board.global.Timestamped;

import com.board.board.like.entity.LikeComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String reply;

    @Column(nullable = false)
    private String username;


    @OneToMany
    @JoinColumn(name = "commentId")
    private List<LikeComment> likeCommentList = new ArrayList<>();

    private Long likeCount = 0L;


    @OneToMany
    private List<LikeComment> commentLike = new ArrayList<>();


    public void update(String reply) {
        this.reply = reply;
    }

    public void addLike(LikeComment likeComment) {
        this.likeCommentList.add(likeComment);
        increaseLike();
    }

    public void increaseLike() {
        this.likeCount++;
    }


    public void reductionLike() {
        this.likeCount--;
    }
}
