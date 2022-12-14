package com.board.board.post.dto;

import com.board.board.post.entity.Post;
import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String content;



    public Post toEntity(String username){
        return Post.builder().username(username).likeCount(0L).title(title).content(content).build();
    }
}
