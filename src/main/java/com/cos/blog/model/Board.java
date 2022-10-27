package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,length = 100)
    private String title;

    @Lob //대용량 데이터를 사용할때 씀
    private String content;

    @ColumnDefault("0")
    private int count; //조회수

    @CreationTimestamp //데이터가 생성될때 자동으로 업데이트
    private Timestamp createDate;

    @ManyToOne //Many = Board, User = One 한명의 유저는 여러개글쓰기가능
    @JoinColumn(name = "userId")
    private User user;

    //mappedBy 는 연관관계의 주인이 아니다 FK가 아니다 DB에 컬럼이 필요없다는얘기
    @OneToMany(mappedBy = "board") // One = Board Many = reply
    private List<Reply> reply; //댓글이 여러개이기때문에 리스트로 받아온다.


}
