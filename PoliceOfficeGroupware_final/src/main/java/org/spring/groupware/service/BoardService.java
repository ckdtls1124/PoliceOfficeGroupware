package org.spring.groupware.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupware.dto.BoardDto;
import org.spring.groupware.entity.BoardEntity;
import org.spring.groupware.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 작성
    @Transactional
    public void boardWrite(BoardDto boardDto) {

        BoardEntity boardEntity = BoardEntity.toboardEntity(boardDto);

        boardRepository.save(boardEntity);
    }

    // 게시글 목록 페이징
    public Page<BoardDto> boarListPaging(Pageable pageable) {

        Page<BoardEntity> boardEntities = boardRepository.findAll(pageable);

        Page<BoardDto> boardDtos = boardEntities.map(BoardDto::toboardDto);

        return boardDtos;
    }

    // 게시글 검색목록 페이징
    public Page<BoardDto> boarListSearchPaging(String search, Pageable pageable) {

        Page<BoardEntity> boardEntities = boardRepository.findByBoardTitleContaining(search, pageable);

        Page<BoardDto> boardDtos = boardEntities.map(BoardDto::toboardDto);

        return boardDtos;
    }

    // 게시글 상세 목록
    public BoardDto boardDetailList(Long boardId) {

        Optional<BoardEntity> boardEntity = boardRepository.findByBoardId(boardId);

        if (boardEntity.isPresent()) {
            BoardDto boardDto = BoardDto.toboardDto(boardEntity.get());

            return boardDto;
        } else {
            return null;
        }
    }

    // 게시글 조회시 조회수 1증가
    @Transactional
    public void upViews(Long boardId) {

        boardRepository.updateViews(boardId);

    }

    // 게시글 상세목록페이지에서 댓글 등록, 수정, 삭제시 조회수 증가방지
    @Transactional
    public void noUpViews(Long boardId) {

        boardRepository.updateViews2(boardId);

    }

    // 게시글 수정
    @Transactional
    public BoardDto boardUpdate(Long boardId) {

        Optional<BoardEntity> boardEntity = boardRepository.findByBoardId(boardId);

        if (boardEntity.isPresent()) {
            BoardDto boardDto = BoardDto.toboardDto(boardEntity.get());

            return boardDto;
        } else {
            return null;
        }
    }

    // 게시글 수정 실행
    @Transactional
    public void boardUpdateOk(BoardDto boarDto) {

        BoardEntity boardEntity = BoardEntity.toAllboardEntity(boarDto);

        boardRepository.save(boardEntity);
    }

    // 게시글 삭제
    @Transactional
    public void boardDelete(Long boardId) {

        boardRepository.deleteByBoardId(boardId);
    }

    // 당일 작성한 게시글들만 출력(main 페이지)
    public List<BoardDto> todayBoard() {

        List<BoardEntity> boardEntities = boardRepository.findTodayBoard();
        List<BoardDto> boardDtos = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntities) {
            boardDtos.add(BoardDto.toboardDto(boardEntity));
        }
        return boardDtos;
    }


}
