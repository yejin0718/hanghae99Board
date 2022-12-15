package com.board.board.comment.dto;

import com.board.board.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String username;
    private String reply;
    private LocalDateTime createdAt;

    public CommentResponseDto (Comment comment){
        this.id = comment.getId();
        this.username = comment.getUsername();
        this.reply = comment.getReply();
        this.createdAt =comment.getCreatedAt();
    }

}
