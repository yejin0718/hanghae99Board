package com.board.board.post.dto;

import com.board.board.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;

    public PostResponseDto(Post post){
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getMember().getUsername();
        this.createdAt = post.getCreatedAt();
    }

}
