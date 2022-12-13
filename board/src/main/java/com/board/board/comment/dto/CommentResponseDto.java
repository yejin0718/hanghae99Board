package com.board.board.comment.dto;

import com.board.board.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private String username;
    private String reply;
    private LocalDateTime createdAt;

    public CommentResponseDto (Comment comment){
        this.username = comment.getMember().getUsername();
        this.reply = comment.getReply();
        this.createdAt =comment.getCreatedAt();
    }

}
