package com.board.board.comment.service;

import com.board.board.comment.dto.CommentRequestDto;
import com.board.board.comment.dto.CommentResponseDto;
import com.board.board.comment.entity.Comment;
import com.board.board.comment.repository.CommentRepository;
import com.board.board.global.config.UserRoleEnum;
import com.board.board.member.entity.Member;
import com.board.board.post.entity.Post;
import com.board.board.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    /* 댓글 작성 */
    @Override
    @Transactional
    public CommentResponseDto writeComment(Long postId, CommentRequestDto requestDto, Member member){

        Comment comment = requestDto.toEntity(member.getUsername());
        commentRepository.save(comment);

        Post post = checkPost(postId);

        post.addComment(comment);

        return new CommentResponseDto(comment);
    }

    /* 댓글 수정 */
    @Override
    @Transactional
    public CommentResponseDto editComment(Long postId, Long commentId, CommentRequestDto requestDto) {

        checkPost(postId);

        String reply = requestDto.getReply();

        Comment comment = checkComment(commentId);
        comment.update(reply);


        return new CommentResponseDto(comment);
    }

    /* 댓글 삭제 */
    @Override
    @Transactional
    public void deleteComment(Long postId, Long commentId, Member member) {

        checkPost(postId);

        String username = member.getUsername();
        UserRoleEnum role = member.getRole();

        Comment comment = checkComment(commentId);

        checkRole(comment, role, username);

        commentRepository.delete(comment);
    }

    @Override
    @Transactional
    public CommentResponseDto writeReComment(Long commentId, CommentRequestDto requestDto, Member member){
        Comment comment = requestDto.toEntity(member.getUsername());
        comment = commentRepository.save(comment);
        Comment parentComment = checkComment(commentId);

        parentComment.addComment(comment);
        System.out.println("CommentServiceImpl.writeReComment");
        return new CommentResponseDto(comment);
    }

    /* 유저 권한 확인  */
    private void checkRole(Comment comment, UserRoleEnum role, String username) {
        if(role != UserRoleEnum.ADMIN){
            if(!comment.getUsername().equals(username)){
                throw new IllegalArgumentException("게시물의 권한이 없습니다.");
            }
        }
    }

    /* 댓글 확인  */
    private Comment checkComment(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않은 댓글입니다.")
        );
    }
    /* 게시글 확인  */
    private Post checkPost(Long postId){
        return postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않은 게시물입니다.")
        );
    }




}
