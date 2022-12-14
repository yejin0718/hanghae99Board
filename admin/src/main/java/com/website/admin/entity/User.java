package com.website.admin.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users") //테이블 이름 바꿔주는얘인데 User는 다른 설정에서 쓰이는 이름이라 앵간하면 쓰지마요
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId; // '_'는 안쓰는게 좋아요 대문자로 구분!

    @Column(nullable = false)
    private Integer age;

    public User(String userId, Integer age){
        this.userId = userId;
        this.age = age;
    }

    public void update(String userId, Integer age) {
        this.userId = userId;
        this.age = age;
    }
}
