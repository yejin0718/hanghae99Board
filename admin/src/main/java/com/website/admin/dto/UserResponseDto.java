package com.website.admin.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor //모든 필드 생성자 만들어주는거
@NoArgsConstructor //필드 사용 안하는 생성자 만들어주는거
public class UserResponseDto {

    private String userId;
    private Integer age;

//    UserResponseDto(String userId, Integer age){
//        this.userId = userId;
//        this.age = age;
//    }
    //원래 이거 사용하지만 @AllArgsConstructor 이 어노테이션이 있어서 없어도되요
}
