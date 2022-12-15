package com.board.board.comment.controller;

import com.board.board.comment.dto.CommentRequestDto;
import com.board.board.comment.dto.CommentResponseDto;
import com.board.board.comment.service.CommentService;
import com.board.board.global.ResponseMessage;
import com.board.board.global.security.MemberDetailsImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = {"댓글 API Controller"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class CommentController {

    private final CommentService commentService;


    @ApiOperation(value = "댓글 작성")
    /* 댓글작성 */
    @PostMapping("/{postId}/comment")
    public ResponseEntity<ResponseMessage> writeComment(
            @PathVariable Long postId,
            @RequestBody CommentRequestDto requestDto,
            @ApiIgnore @AuthenticationPrincipal MemberDetailsImpl memberDetails){

        CommentResponseDto commentResponseDto = commentService.writeComment(postId, requestDto, memberDetails.getMember());

        ResponseMessage responseMessage = new ResponseMessage("댓글 작성 성공", 200, commentResponseDto);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }


    @ApiOperation(value = "댓글 수정")
    /* 댓글수정 */
    @PatchMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<ResponseMessage> editComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto requestDto,
            @ApiIgnore @AuthenticationPrincipal MemberDetailsImpl memberDetails){
        CommentResponseDto commentResponseDto = commentService.editComment(postId, commentId, requestDto);

        ResponseMessage responseMessage = new ResponseMessage("댓글 수정 성공", 200, commentResponseDto);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


    @ApiOperation(value = "댓글 삭제")
    /* 댓글삭제 */
    @DeleteMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<ResponseMessage> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @ApiIgnore @AuthenticationPrincipal MemberDetailsImpl memberDetails
    ) {
        commentService.deleteComment(postId, commentId, memberDetails.getMember());

        ResponseMessage responseMessage = new ResponseMessage("댓글 삭제 성공", 200, null);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


    @ApiOperation(value = "대댓글")
    /* 댓글작성 */
    @PostMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<ResponseMessage> CreateReComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto requestDto,
            @ApiIgnore @AuthenticationPrincipal MemberDetailsImpl memberDetails){

        CommentResponseDto commentResponseDto = commentService.writeReComment(commentId, requestDto, memberDetails.getMember());

        ResponseMessage responseMessage = new ResponseMessage("대댓글 작성 성공", 200, commentResponseDto);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }


}
