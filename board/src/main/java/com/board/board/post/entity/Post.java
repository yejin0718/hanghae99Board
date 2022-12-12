package com.board.board.post.entity;

import com.board.board.global.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Member가 추가되면 밑에 있는 연관관계로 바꾸기 */
    /* 그 전까진 username = "test" 로 테스트 */
    @Column(nullable = false)
    private String username;

    @Column(nullable =false)
    private String title;

    @Column(nullable = false)
    private String content;

    /*
     *@ManyToOne(fetch = LAZY)
     *@JoinColumn(name = "member_id")
     *private Member member;
     */

    /* 연관관계 매서드 추가할 것 */





}
