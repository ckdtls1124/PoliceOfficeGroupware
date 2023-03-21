package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.BoardEntity;
import org.spring.p21suck2jo.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<ReplyEntity,Long> {

    List<ReplyEntity> findAllByBoardEntityOrderByReplyIdDesc(BoardEntity boardEntity);

    Optional<ReplyEntity> findByReplyId(Long replyId);

    /*List<ReplyEntity> findAllByBoardEntityOrderByBoardIdDesc(BoardEntity boardEntity);*/
}
