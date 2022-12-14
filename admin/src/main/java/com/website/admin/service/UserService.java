package com.website.admin.service;

import com.website.admin.dto.UserRequestDto;
import com.website.admin.dto.UserResponseDto;
import com.website.admin.entity.User;
import com.website.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    @Transactional(readOnly = true)
    public List<UserResponseDto> getUserList() {
        List<User> userList  = userRepository.findAll();

        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for(User user : userList){
            userResponseDtoList.add(new UserResponseDto(user.getUserId(), user.getAge()));
        }

        return userResponseDtoList;
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("없는 User입니다.") //못찾으면 에러 던지는 만약 이 에러 나면 없는거니 그렇게 유추하면 됩니다.
        );
        UserResponseDto userResponseDto = new UserResponseDto(user.getUserId(), user.getAge());
        return userResponseDto;
    }

    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        String userId = userRequestDto.getUserId();
        Integer age = userRequestDto.getAge();
        User requestUser = userRepository.findByUserId(userId).orElse(null);

        if(requestUser != null){ //null이 아니면 이미 존재한다는 말
            throw new IllegalArgumentException("중복된 User가 존재합니다.");
        }

        User user = userRepository.save(new User(userId, age));

        UserResponseDto userResponseDto = new UserResponseDto(user.getUserId(), user.getAge());

        return userResponseDto;

    }


    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        String userId = userRequestDto.getUserId();
        Integer age = userRequestDto.getAge();
        User user = userRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("존재하지 않는 User입니다.")
        );

        user.update(userId, age);

        UserResponseDto userResponseDto = new UserResponseDto(user.getUserId(), user.getAge());

        return userResponseDto;
    }

    @Transactional
    public Boolean deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("존재하지 않는 User입니다.")
        );
        userRepository.deleteById(id);
        return true;
    }
}
