package com.board.board.comment.dto;

import com.board.board.comment.entity.Comment;
import com.board.board.post.entity.Post;

public class CommentRequestDto {

    private String reply;

    public Comment toEntity(String username){
        return Comment.builder().username(username).reply(reply).build();
    }
}
