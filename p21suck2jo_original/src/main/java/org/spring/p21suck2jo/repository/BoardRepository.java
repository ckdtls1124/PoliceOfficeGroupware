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

    Page<BoardEntity> findByBoardTitleContaining(String search, Pageable pageable);

    Optional<BoardEntity> findByBoardId(Long boardId);


    Optional<BoardEntity> deleteByBoardId(Long boardId);

    @Modifying
    @Query(value = "update BoardEntity b set b.views=b.views+1 where b.boardId=:boardId")
    void updateViews(Long boardId);


    @Modifying
    @Query(value = "update BoardEntity b set b.views=b.views where b.boardId=:boardId")
    void updateViews2(Long boardId);

    @Query(value = "select * from board where create_time > current_date()",nativeQuery = true)
    List<BoardEntity> findTodayBoard();
}
