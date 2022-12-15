package com.board.board.post.service;

import com.board.board.global.config.UserRoleEnum;
import com.board.board.global.security.MemberDetailsImpl;
import com.board.board.member.entity.Member;
import com.board.board.member.repository.MemberRepository;
import com.board.board.post.dto.PostRequestDto;
import com.board.board.post.dto.PostResponseDto;
import com.board.board.post.dto.PostResponseListDto;
import com.board.board.post.entity.Post;
import com.board.board.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /* 전체 게시물 목록 조회 */
    @Override
    @Transactional(readOnly = true)
    public PostResponseListDto getPostList(String username) {

        PostResponseListDto postList = new PostResponseListDto();
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        for(Post post : posts) {
            postList.addPost(new PostResponseDto(post, username));
        }
        return postList;
    }

    /* 게시물 상세 조회 */
    @Override
    @Transactional(readOnly = true)
    public PostResponseDto getPostInfo(Long postId, String username) {
        Post post = checkPost(postId);
        return new PostResponseDto(post, username);
    }

    /* 게시물 작성 */
    @Override
    @Transactional
    public PostResponseDto writePost(PostRequestDto requestDto, String username){

        checkMember(username);

        Post post = requestDto.toEntity(username);//username은 request에 포함이 안되므로 따로 추가해줘야한다.

        postRepository.save(post);

        return new PostResponseDto(post);
    }

    /* 게시물 수정 */
    @Override
    @Transactional
    public PostResponseDto editPost(Long postId, PostRequestDto requestDto, Member member){
        /* 유저 인증 정보 */
        String username = member.getUsername();
        UserRoleEnum role = member.getRole();

        /* 요청 데이터 가져오기 */
        String title = requestDto.getTitle();
        String content = requestDto.getContent();

        Post post = checkPost(postId);
        checkRole(post, role, username);

        post.update(title, content);

        return new PostResponseDto(post);

    }

    /* 게시물 삭제 */
    @Override
    @Transactional
    public void deletePost(Long postId, Member member) {
        /* 유저 인증 정보 */
        String username = member.getUsername();
        UserRoleEnum role = member.getRole();

        Post post = checkPost(postId);
        checkRole(post, role, username);

        postRepository.delete(post);
    }



    /* 게시물 확인 */
    private Post checkPost(Long postId){
        return postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않은 게시물입니다.")
        );
    }

    private Member checkMember(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(()->new IllegalArgumentException("로그인이 필요합니다."));
    }

    /* 게시물 권환 확인 */
    private void checkRole(Post post, UserRoleEnum role, String username){
        if(role != UserRoleEnum.ADMIN){
            if(!post.getUsername().equals(username)){
                throw new IllegalArgumentException("게시물의 권한이 없습니다.");
            }
        }
    }

}
