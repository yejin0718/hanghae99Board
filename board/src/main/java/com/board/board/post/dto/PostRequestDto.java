package com.board.board.post.dto;

import com.board.board.comment.entity.Comment;
import com.board.board.post.entity.Post;
import lombok.Getter;

import java.util.List;

@Getter
public class PostRequestDto {
    private String title;
    private String content;

    public Post toEntity(String username){
        return Post.builder().username(username).title(title).content(content).build();
    }
}
