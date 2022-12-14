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

    @Column(nullable = false)
    private Long likeCount;

    @OneToMany
    private List<LikeComment> commentLike = new ArrayList<>();

}
