package com.board.board.comment.dto;

import com.board.board.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    private String reply;

    public Comment toEntity(String username){return Comment.builder().username(username).likeCount(0L).reply(reply).build();}
}
