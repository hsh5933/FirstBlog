package com.cos.blog.repository;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply,Integer> {

    @Query(value="insert into reply(userId, boardId, content, createDate) values(?1,?2,?3,now())",nativeQuery = true)
    void mSave(int userId, int boardId, String content);
}
