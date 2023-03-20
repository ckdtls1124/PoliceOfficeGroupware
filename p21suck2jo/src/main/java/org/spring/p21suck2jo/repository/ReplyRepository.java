package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.BoardEntity;
import org.spring.p21suck2jo.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity,Long> {

    List<ReplyEntity> findAllByBoardEntityOrderByReplyIdDesc(BoardEntity boardEntity);

    /*List<ReplyEntity> findAllByBoardEntityOrderByBoardIdDesc(BoardEntity boardEntity);*/
}
