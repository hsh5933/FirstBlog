package com.cos.blog.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity //User클래스가 MySql에 테이블이 생성이된다.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert //인서트할때 Null인값을 제외시킨다
public class User {
    @Id //primary key
    //프로젝트에서 연결된 DB의 넘버링 전략을 따라감
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //시퀀스 auto_increment

    @Column(nullable = false,length = 100,unique = true) //null이될수없고 길이가 20, unique=true는 아이디 중복불가하게끔
    private String username; //아이디

    @Column(nullable = false,length = 100)
    private String password;

    @Column(nullable = false,length = 50)
    private String email;


    //@ColumnDefault("user")
    //DB는 RoleType이라는게 없다.
    @Enumerated(EnumType.STRING)
    private RoleType role; //Enum을쓰는게 좋다. //admin,user,manager

    private String oauth; //kakao, google 로그인확인

    @CreationTimestamp // 시간이 자동으로 입력되는 어노테이션
    private Timestamp createDate;

}
