package com.board.board.post.dto;

import com.board.board.comment.dto.CommentResponseDto;
import com.board.board.comment.entity.Comment;
import com.board.board.like.entity.LikeComment;
import com.board.board.like.entity.LikePost;
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

    private Long likeCount;
    private LocalDateTime createdAt;
    private List<CommentResponseDto> commentList = new ArrayList<>();
    private List<LikePost> checkPostLike = new ArrayList<>();
    private boolean isPostLike;

    public PostResponseDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUsername();
        this.likeCount = post.getLikeCount();
        this.createdAt = post.getCreatedAt();
    }

    /* 조회 시 해당 유저가 좋아요를 눌렀는지 확인 */
    public PostResponseDto(Post post, String username){
        this(post);

        List<Comment> comments = post.getCommentList();
        if (comments != null) {
            for (Comment comment : comments) {
                this.commentList.add(new CommentResponseDto(comment, username));
            }
        }
        this.isPostLike = false;
        for (LikePost likePost : checkPostLike) {
            if (!isPostLike && likePost.getUsername().equals(username)) {
                this.isPostLike = true;
                break;
            }
        }
    }
}
