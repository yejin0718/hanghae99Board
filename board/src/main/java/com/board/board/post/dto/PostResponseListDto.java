package com.board.board.post.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostResponseListDto {

    private final List<PostResponseDto> postResponseList;

    /* 생성자 생성 시 리스트 생성 */
    public PostResponseListDto(){
        this.postResponseList=new ArrayList<>();
    }

    /* 리스트에 값 추가*/
    public void addPost(PostResponseDto postResponseDto){
        postResponseList.add(postResponseDto);
    }
}
