package com.board.board.post.service;

import com.board.board.post.dto.PostResponseDto;
import com.board.board.post.entity.Post;
import com.board.board.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDto> getPostList() {
        List<PostResponseDto> postResponseList = new ArrayList<>();
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        for(Post post : posts){
            PostResponseDto postResponseDto = new PostResponseDto(post);
            postResponseList.add(postResponseDto);
        }

        return postResponseList;
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponseDto getPostInfo(Long postId) {
        Post post = checkPost(postId);
        return new PostResponseDto(post);
    }








    private Post checkPost (Long id){
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));
    }
}
