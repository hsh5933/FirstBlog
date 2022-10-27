package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity //User클래스가 MySql에 테이블이 생성이된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id //primary key
    //프로젝트에서 연결된 DB의 넘버링 전략을 따라감
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //시퀀스 auto_increment

    @Column(nullable = false,length = 20) //null이될수없고 길이가 20
    private String username; //아이디

    @Column(nullable = false,length = 100)
    private String password;

    @Column(nullable = false,length = 50)
    private String email;

    @ColumnDefault("'user'")
    private String role; //Enum을쓰는게 좋다. //admin,user,manager

    @CreationTimestamp // 시간이 자동으로 입력되는 어노테이션
    private Timestamp createDate;

}
