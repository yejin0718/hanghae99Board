package com.board.board.comment.controller;

import com.board.board.comment.dto.CommentRequestDto;
import com.board.board.comment.dto.CommentResponseDto;
import com.board.board.comment.service.CommentService;
import com.board.board.global.ResponseMessage;
import com.board.board.global.security.MemberDetailsImpl;
import com.board.board.post.dto.PostRequestDto;
import com.board.board.post.dto.PostResponseDto;
import com.board.board.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class CommentController {

    private final CommentService commentService;

    //댓글작성
    @PostMapping("/{postId}/comment")
    public ResponseEntity<ResponseMessage> writeComment(
            @PathVariable Long postId,
            @RequestBody CommentRequestDto requestDto,
            @AuthenticationPrincipal MemberDetailsImpl memberDetails){

        CommentResponseDto commentResponseDto = commentService.writeComment(postId, requestDto, memberDetails.getMember());

        ResponseMessage responseMessage = new ResponseMessage("댓글 작성 성공", 200, commentResponseDto);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }

    @PostMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<ResponseMessage> editComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto requestDto,
            @AuthenticationPrincipal MemberDetailsImpl memberDetails){
        CommentResponseDto commentResponseDto = commentService.editComment(postId, commentId, requestDto, memberDetails.getMember());

        ResponseMessage responseMessage = new ResponseMessage("댓글 수정 성공", 200, commentResponseDto);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


}
