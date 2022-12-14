package com.board.board.post.controller;

import com.board.board.global.ResponseMessage;
import com.board.board.global.security.MemberDetailsImpl;
import com.board.board.post.dto.PostRequestDto;
import com.board.board.post.dto.PostResponseDto;
import com.board.board.post.dto.PostResponseListDto;
import com.board.board.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    /* 전체 게시물 조회 */
    @GetMapping
    public ResponseEntity<ResponseMessage> getPostList(){
        PostResponseListDto postResponseListDto = postService.getPostList();
        ResponseMessage responseMessage = new ResponseMessage( "전체 게시글 목록 조회 성공", 200, postResponseListDto);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    /* 상세 게시물 조회 */
    @GetMapping("/{postId}")
    public ResponseEntity<ResponseMessage> getPostInfo(@PathVariable Long postId){
        PostResponseDto postResponseDto = postService.getPostInfo(postId);
        ResponseMessage responseMessage = new ResponseMessage( "상세 게시글 조회 성공", 200, postResponseDto);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    /* 게시물 작성  */
    @PostMapping
    public ResponseEntity<ResponseMessage> writePost(
            @RequestBody PostRequestDto requestDto,
            @AuthenticationPrincipal MemberDetailsImpl memberDetails){

        PostResponseDto postResponseDto = postService.writePost(requestDto, memberDetails.getUsername());

        ResponseMessage responseMessage = new ResponseMessage("게시글 작성 성공", 200, postResponseDto);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    /* 게시물 수정 */
    @PatchMapping("/{postId}")
    public ResponseEntity<ResponseMessage> editPost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto requestDto,
            @AuthenticationPrincipal MemberDetailsImpl memberDetails){

        PostResponseDto postResponseDto = postService.editPost(postId, requestDto, memberDetails.getMember());
        ResponseMessage responseMessage = new ResponseMessage("게시글 수정 성공", 200, postResponseDto);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    /* 게시물 삭제 */
    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseMessage> deletePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal MemberDetailsImpl memberDetails
    ) {

        postService.deletePost(postId, memberDetails.getMember());

        ResponseMessage responseMessage = new ResponseMessage("게시글 삭제 성공", 200, null);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}