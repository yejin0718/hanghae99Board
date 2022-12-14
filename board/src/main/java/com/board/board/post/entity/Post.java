package com.board.board.post.entity;

import com.board.board.comment.entity.Comment;
import com.board.board.global.Timestamped;
import com.board.board.like.entity.LikePost;
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

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "postId")
    private List<Comment> commentList = new ArrayList<>();



    @OneToMany
    @JoinColumn(name = "postId")
    private List<LikePost> postLikeList = new ArrayList<>();

    private Long likeCount = 0L;


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void increaseLike(){
        this.likeCount++;
    }
    public void reductionLike(){
        this.likeCount--;
    }

    /* 연관관계 편의 메서드 */
    public void addComment(Comment comment){
        this.commentList.add(comment);
    }

    public void addLike(LikePost likePost){
        this.postLikeList.add(likePost);

    }

}
