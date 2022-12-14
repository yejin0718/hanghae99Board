package com.board.board.comment.entity;

import com.board.board.global.Timestamped;

import com.board.board.like.entity.LikeComment;
import com.board.board.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

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

    private Long likeCount = 0L;


    @OneToMany(cascade = CascadeType.REMOVE)
    private List<LikeComment> commentLike = new ArrayList<>();


    public void update(String reply) {
        this.reply = reply;
    }

}
