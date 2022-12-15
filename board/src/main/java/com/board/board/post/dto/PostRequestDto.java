package com.board.board.post.dto;

import com.board.board.post.entity.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "게시글 관련 요청 정보")
public class PostRequestDto {
    @ApiModelProperty(value = "게시글 제목", dataType = "String", example = "제목1")
    private String title;
    @ApiModelProperty(value = "게시글 내용", dataType = "String", example = "내용1")
    private String content;

    public Post toEntity(String username){
        return Post.builder().username(username).likeCount(0L).title(title).content(content).build();
    }
}
