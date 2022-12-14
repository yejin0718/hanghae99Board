package com.board.board.post.entity;

import com.board.board.comment.entity.Comment;
import com.board.board.global.Timestamped;
import com.board.board.member.entity.Member;
import com.board.board.post.dto.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

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


    @Column(nullable = false)
    private String username;

    @OneToMany
    private List<Comment> commentList;

    @Column(nullable = false)
    private Long likeCount;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /* 연관관계 편의 메서드 */
    public void addComment(Comment comment){
        this.commentList.add(comment);
    }

}
