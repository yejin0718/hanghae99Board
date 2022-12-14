package com.website.admin.controller;


import com.website.admin.dto.UserRequestDto;
import com.website.admin.dto.UserResponseDto;
import com.website.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/user") //url은 해당 자원을 표시하는게 좋아요 list X -> user O
    public List<UserResponseDto> getUserList(){
        List<UserResponseDto> userResponseDtoList = userService.getUserList(); //비즈니스 로직 등은 Service에서
        //Entity는 밖에 나가면 안돼요 항상 Dto로
        return userResponseDtoList;
    }

    @GetMapping("/user/{id}")
    public UserResponseDto getUser(@PathVariable Long id){ //id의 경우 크기가 매우 클수 있으니 그냥 Long로
        UserResponseDto userResponseDto = userService.getUser(id);
        return userResponseDto;
    }

    @PostMapping("/user")
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = userService.createUser(userRequestDto);

        return userResponseDto;
    }

    @PutMapping("/user/{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = userService.updateUser(id, userRequestDto);

        return userResponseDto;
    }

    @DeleteMapping ("/user/{id}")
    public boolean updateUser(@PathVariable Long id){ // Delete는 앵간하면 안쓰는걸로
        Boolean deleteChecek = userService.deleteUser(id);
        return deleteChecek;
    }

}
