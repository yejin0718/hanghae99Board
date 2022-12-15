package com.board.board.comment.dto;

import com.board.board.comment.entity.Comment;
import com.board.board.like.entity.LikeComment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String username;
    private String reply;
    private Long likeCount;
    private LocalDateTime createdAt;
    private boolean isCommentLike;

    private List<CommentResponseDto> reCommentList = new ArrayList<>();

    public CommentResponseDto (Comment comment){
        this.id = comment.getId();
        this.username = comment.getUsername();
        this.reply = comment.getReply();
        this.likeCount = comment.getLikeCount();
        this.createdAt =comment.getCreatedAt();
    }

    public CommentResponseDto (Comment comment, String username){
        this(comment);
        this.isCommentLike = false;

        List<Comment> commentList = comment.getCommentList();
        if(!commentList.isEmpty()){
            for(Comment temp : commentList){
                this.reCommentList.add(new CommentResponseDto(temp, username));
            }
        }

        for (LikeComment likeComment : comment.getCommentLike()) {
            if (!isCommentLike && likeComment.getUsername().equals(username)) {
                this.isCommentLike = true;
                break;
            }
        }

    }
}
