package com.board.board.post.controller;

import com.board.board.global.ResponseMessage;
import com.board.board.post.dto.PostResponseDto;
import com.board.board.post.dto.PostResponseListDto;
import com.board.board.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    @GetMapping
    public ResponseEntity<ResponseMessage> getPostList(){
        PostResponseListDto postResponseListDto = postService.getPostList();
        ResponseMessage responseMessage = new ResponseMessage( "전체 게시글 목록 조회 성공", 200, postResponseListDto);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<ResponseMessage> getPostInfo(@PathVariable Long postId){
        PostResponseDto postResponseDto = postService.getPostInfo(postId);
        ResponseMessage responseMessage = new ResponseMessage( "상세 게시글 조회 성공", 200, postResponseDto);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);


    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseMessage> deletePost(@PathVariable Long postId) {
        PostResponseDto postResponseDto = postService.deletePost(postId);
        ResponseMessage responseMessage = new ResponseMessage("게시글 삭제 성공", 200, postResponseDto);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


}
    /*
    예제
    @PostMapping
    public ResponseEntity<ResponseMessage> test(){
    PostResponseDto postResponseDto = postService.someMethod();
    ResponseMessage responseMessage = new ResponseMessage("실행",200, postResponseDto);
    return new ResponseEntity(responseMessage, HttpStatus.OK);
    }
    */