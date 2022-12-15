package com.board.board.post.service;

import com.board.board.member.entity.Member;
import com.board.board.post.dto.PostRequestDto;
import com.board.board.post.dto.PostResponseDto;
import com.board.board.post.dto.PostResponseListDto;

public interface PostService {
    PostResponseListDto getPostList(String username);

    PostResponseDto getPostInfo(Long postId, String username);

    void deletePost(Long postId, Member member);

    PostResponseDto writePost(PostRequestDto requestDto, String username);

    PostResponseDto editPost(Long postId, PostRequestDto requestDto, Member member);
}
