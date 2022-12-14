package com.board.board.post.dto;

import com.board.board.comment.dto.CommentResponseDto;
import com.board.board.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

    public PostResponseDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUsername();
        this.createdAt = post.getCreatedAt();
        this.commentResponseDtoList = post.getCommentList().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
}
