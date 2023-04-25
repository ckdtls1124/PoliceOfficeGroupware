package org.spring.groupware.repository;

import org.spring.groupware.entity.BoardEntity;
import org.spring.groupware.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<ReplyEntity,Long> {

    // boardEntity에 있는 replyId를 내림차순으로 정렬하는 쿼리메소드
    List<ReplyEntity> findAllByBoardEntityOrderByReplyIdDesc(BoardEntity boardEntity);

    Optional<ReplyEntity> findByReplyId(Long replyId);

    Optional<ReplyEntity> deleteByReplyId(Long replyId);

}
