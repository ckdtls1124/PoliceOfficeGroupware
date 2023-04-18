package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.BaseEntity;
import org.spring.p21suck2jo.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    // 검색된 목록만 페이징 처리하는 쿼리 메소드
    Page<BoardEntity> findByBoardTitleContaining(String search, Pageable pageable);

    // boardId 찾는 쿼리 메소드
    Optional<BoardEntity> findByBoardId(Long boardId);

    // boardId 삭제하는 쿼리 메소드
    Optional<BoardEntity> deleteByBoardId(Long boardId);

    // boardId의 해당하는 views를 +1 해주는 쿼리 메소드
    @Modifying
    @Query(value = "update BoardEntity b set b.views=b.views+1 where b.boardId=:boardId")
    void updateViews(Long boardId);

    @Modifying
    @Query(value = "update BoardEntity b set b.views=b.views where b.boardId=:boardId")
    void updateViews2(Long boardId);

    // 오늘 작성한 게시글을 찾는 쿼리 메소드
    @Query(value = "select * from board where create_time > current_date()",nativeQuery = true)
    List<BoardEntity> findTodayBoard();
}
