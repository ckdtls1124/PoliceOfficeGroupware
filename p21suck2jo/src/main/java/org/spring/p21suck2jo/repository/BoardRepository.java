package org.spring.p21suck2jo.repository;

import org.spring.p21suck2jo.entity.BaseEntity;
import org.spring.p21suck2jo.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    Page<BoardEntity> findByBoardTitleContaining(String search, Pageable pageable);

    Optional<BoardEntity> findByBoardId(Long boardId);


}
