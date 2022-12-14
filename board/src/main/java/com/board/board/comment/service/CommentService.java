package com.board.board.comment.service;

import com.board.board.comment.dto.CommentRequestDto;
import com.board.board.comment.dto.CommentResponseDto;
import com.board.board.member.entity.Member;

public interface CommentService {

    CommentResponseDto writeComment(Long postId, CommentRequestDto requestDto, Member member);

    CommentResponseDto editComment(Long postId, Long commentId, CommentRequestDto requestDto, Member member);
}
