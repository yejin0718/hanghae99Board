package com.board.board.post.service;

import com.board.board.post.dto.PostResponseDto;

import java.util.List;

public interface PostService {
    public List<PostResponseDto> getPostList();

    public PostResponseDto getPostInfo(Long postId);

    public PostResponseDto deletePost(Long postId);

}
