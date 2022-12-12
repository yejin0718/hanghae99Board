package com.board.board.post.service;

import com.board.board.post.dto.PostResponseDto;
import com.board.board.post.dto.PostResponseListDto;

public interface PostService {
    public PostResponseListDto getPostList();

    public PostResponseDto getPostInfo(Long postId);

    public PostResponseDto deletePost(Long postId);

}
