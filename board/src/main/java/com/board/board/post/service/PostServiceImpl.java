package com.board.board.post.service;

import com.board.board.global.config.UserRoleEnum;
import com.board.board.member.dto.MemberRequestDto;
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
    public PostResponseListDto getPostList() {
        PostResponseListDto postResponseListDto = new PostResponseListDto();
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        for(Post post : posts) {
            postResponseListDto.addPost(new PostResponseDto(post));
        }
        return postResponseListDto;
    }

    /* 게시물 상세 조회 */
    @Override
    @Transactional(readOnly = true)
    public PostResponseDto getPostInfo(Long postId) {
        return new PostResponseDto(checkPost(postId));
    }

    //작성
    @Override
    @Transactional
    public PostResponseDto writePost(PostRequestDto requestDto, String username){

        Member member = memberRepository.findByUsername(username).orElseThrow(()->new IllegalArgumentException("로그인이 필요합니다."));
        Post post = requestDto.toEntity();
        post.changeMember(member);

        postRepository.save(post);

        return new PostResponseDto(post);
    }

    //수정
    @Override
    @Transactional
    public PostResponseDto editPost(Long postId, PostRequestDto requestDto){
        Post post = checkPost(postId);

        //본인이 작성한 메모인지 확인

        /* userRole */
        // ADMIN, USER 에 따른 게시글 삭제 분기 로직 구현하기

        String title = requestDto.getTitle();
        String content = requestDto.getContent();

        System.out.println("title = " + title);
        System.out.println("content = " + content);

        post.update(title, content);
        return new PostResponseDto(post);

    }

    @Override
    @Transactional
    public PostResponseDto deletePost(Long postId) {
        Post post = checkPost(postId);

        // 51 ~ 59 라인 계속 수정될 부분
        // 멤버 dto 에서 username 꺼내와서 post.getUsername 과 일단 비교 진행
        // 추후에 다시 바꿀 예정
        MemberRequestDto memberRequestDto = new MemberRequestDto();
        String username = memberRequestDto.getUsername();
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        // 게시글 username 과 회원의 username 다르면서, role != admin 이 아닐 경우 => 삭제 권한 없음.
//        if (!(post.getUsername().equals(member.getUsername()) && member.getRole() != UserRoleEnum.ADMIN)) {
//            throw new IllegalArgumentException("삭제 권한이 없습니다.");
//        }

        postRepository.delete(post);

        return new PostResponseDto(post);
    }

    private Post checkPost (Long id){
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));
    }
}
