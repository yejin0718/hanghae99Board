package com.board.board.post.service;

import com.board.board.post.dto.PostResponseDto;

public interface PostService {
    public PostResponseDto getPostList();

    public PostResponseDto getPostInfo(Long postId);

}
