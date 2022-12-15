package com.board.board.comment.dto;

import com.board.board.comment.entity.Comment;
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

    private List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

    public CommentResponseDto (Comment comment){
        this.id = comment.getId();
        this.username = comment.getUsername();
        this.reply = comment.getReply();
        this.likeCount = comment.getLikeCount();
        this.createdAt =comment.getCreatedAt();
        List<Comment> commentList = comment.getCommentList();
        if(!commentList.isEmpty()){
            for(Comment temp : commentList){
                this.commentResponseDtoList.add(new CommentResponseDto(temp));
            }
        }
    }

}
