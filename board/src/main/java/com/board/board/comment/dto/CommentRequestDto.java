package com.board.board.comment.dto;

import com.board.board.comment.entity.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;

@ApiModel("댓글 관련 요청 정보")
@Getter
public class CommentRequestDto {

    @ApiModelProperty(value = "댓글 내용", dataType = "String", example = "댓글1")
    private String reply;

    public Comment toEntity(String username){return Comment.builder().username(username).likeCount(0L).reply(reply).build();}
}
