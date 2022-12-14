package com.board.board.comment.entity;

import com.board.board.global.Timestamped;
import com.board.board.member.entity.Member;
import com.board.board.post.entity.Post;
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

}
