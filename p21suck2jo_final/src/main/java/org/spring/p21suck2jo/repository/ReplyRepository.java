package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.BoardEntity;
import org.spring.p21suck2jo.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<ReplyEntity,Long> {

    // boardEntity에 있는 replyId를 내림차순으로 정렬하는 쿼리메소드
    List<ReplyEntity> findAllByBoardEntityOrderByReplyIdDesc(BoardEntity boardEntity);

    Optional<ReplyEntity> findByReplyId(Long replyId);

    Optional<ReplyEntity> deleteByReplyId(Long replyId);

}
