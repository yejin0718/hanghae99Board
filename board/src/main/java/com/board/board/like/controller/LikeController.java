package com.board.board.like.controller;


import com.board.board.global.ResponseMessage;
import com.board.board.global.security.MemberDetailsImpl;
import com.board.board.like.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = {"좋아요 API Controller"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    @ApiOperation(value = "유저의 댓글 좋아요 판별")
    @PostMapping("/comment/{id}")
    public ResponseEntity<ResponseMessage> pushLikeComment(
            @PathVariable Long id,
            @ApiIgnore @AuthenticationPrincipal MemberDetailsImpl memberDetails
    ){
        ResponseMessage responseMessage = new ResponseMessage("댓글 좋아요", 200, likeService.updateLikeComment(id, memberDetails.getUsername()));

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);


    }

    @ApiOperation(value = "유저의 게시글 좋아요 판별")
    @PostMapping("/post/{id}")
    public ResponseEntity<ResponseMessage> pushLikePost(
            @PathVariable Long id,
            @ApiIgnore @AuthenticationPrincipal MemberDetailsImpl memberDetails
    ){
        ResponseMessage responseMessage = new ResponseMessage("게시물 좋아요", 200, likeService.updateLikePost(id, memberDetails.getUsername()));

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

}
