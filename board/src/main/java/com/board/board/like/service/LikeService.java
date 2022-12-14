package com.board.board.like.service;

import com.board.board.comment.entity.Comment;
import com.board.board.comment.repository.CommentRepository;
import com.board.board.like.entity.LikeComment;
import com.board.board.like.entity.LikePost;
import com.board.board.like.repository.LikeCommentRepository;
import com.board.board.like.repository.LikePostRepository;
import com.board.board.post.entity.Post;
import com.board.board.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikePostRepository likePostRepository;
    private final LikeCommentRepository likeCommentRepository;
    private final PostRepository postRepository;

    private final CommentRepository commentRepository;


    @Transactional
    public Boolean updateLikeComment(Long id, String username) {
        LikeComment likeComment = likeCommentRepository.findByCommentIdAndUsername(id, username).orElse(null);
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시물을 찾을 수 없습니다."));

        if(likeComment == null){
            likeComment = new LikeComment();
            likeComment.setUsername(username);
            likeCommentRepository.save(likeComment);
            comment.addLike(likeComment);
            comment.increaseLike();
            return true;
        }else {
            comment.reductionLike();
            likeCommentRepository.delete(likeComment);
            return false;
        }
    }

    @Transactional
    public Boolean updateLikePost(Long id, String username) {
        LikePost likePost = likePostRepository.findByPostIdAndUsername(id, username).orElse(null);
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시물을 찾을 수 없습니다."));

        if(likePost == null){
            likePost = new LikePost();
            likePost.setUsername(username);
            likePostRepository.save(likePost);
            post.addLike(likePost);
            post.increaseLike();
            return true;
        }else{
            post.reductionLike();
            likePostRepository.delete(likePost);
            return false;
        }


    }
}
