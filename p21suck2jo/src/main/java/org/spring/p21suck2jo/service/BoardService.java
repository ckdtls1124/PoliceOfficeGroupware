package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.BoardDto;
import org.spring.p21suck2jo.entity.BoardEntity;
import org.spring.p21suck2jo.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService{

    private final BoardRepository boardRepository;

    @Transactional
    public void boardWrite(BoardDto boardDto) {

        BoardEntity boardEntity=BoardEntity.toboardEntity(boardDto);

        boardRepository.save(boardEntity);
    }

    public Page<BoardDto> boarListPaging(Pageable pageable) {

        Page<BoardEntity> boardEntities=boardRepository.findAll(pageable);

        Page<BoardDto> boardDtos=boardEntities.map(BoardDto::toboardDto);

        return boardDtos;
    }

    public Page<BoardDto> boarListSearchPaging(String search, Pageable pageable) {

        Page<BoardEntity> boardEntities=boardRepository.findByBoardTitleContaining(search,pageable);

        Page<BoardDto> boardDtos=boardEntities.map(BoardDto::toboardDto);

        return boardDtos;
    }

    public BoardDto boardDetail(Long boardId) {

        Optional<BoardEntity> boardEntity=boardRepository.findByBoardId(boardId);

        if(boardEntity.isPresent()){
            BoardDto boardDto=BoardDto.toboardDto(boardEntity.get());

            return boardDto;
        }else{
            return null;
        }
    }


    @Transactional
    public void upViews(Long boardId) {

        boardRepository.updateViews(boardId);

    }

    @Transactional
    public void upViews2(Long boardId) {

        boardRepository.updateViews2(boardId);

    }

    @Transactional
    public BoardDto boardUpdate(Long boardId) {

        Optional<BoardEntity> boardEntity=boardRepository.findByBoardId(boardId);

        if(boardEntity.isPresent()){
            BoardDto boardDto=BoardDto.toboardDto(boardEntity.get());

            return boardDto;
        }else{
            return null;
        }
    }


    @Transactional
    public void boardUpdateOk(BoardDto boarDto) {

        BoardEntity boardEntity=BoardEntity.toAllboardEntity(boarDto);

        boardRepository.save(boardEntity);
    }

    @Transactional
    public void boardDelete(Long boardId) {

        boardRepository.deleteByBoardId(boardId);
    }

    public List<BoardDto> todayBoard() {

        List<BoardEntity> boardEntities = boardRepository.findTodayBoard();
        List<BoardDto> boardDtos = new ArrayList<>();

        for(BoardEntity boardEntity : boardEntities){
            boardDtos.add(BoardDto.toboardDto(boardEntity));
        }
        return boardDtos;
    }


}
