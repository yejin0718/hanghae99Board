package com.board.board.post.service;

import com.board.board.post.dto.PostRequestDto;
import com.board.board.post.dto.PostResponseDto;
import com.board.board.post.dto.PostResponseListDto;

public interface PostService {
    public PostResponseListDto getPostList();

    public PostResponseDto getPostInfo(Long postId);

    public PostResponseDto deletePost(Long postId);

    public PostResponseDto writePost(PostRequestDto requestDto);

    PostResponseDto editPost(Long postId, PostRequestDto requestDto);
}
