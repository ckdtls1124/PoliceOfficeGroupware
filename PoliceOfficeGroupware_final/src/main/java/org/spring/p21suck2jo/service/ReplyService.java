package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.ReplyDto;
import org.spring.p21suck2jo.entity.BoardEntity;
import org.spring.p21suck2jo.entity.ReplyEntity;
import org.spring.p21suck2jo.repository.ReplyRepository;
import org.spring.p21suck2jo.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    // 댓글 등록
    @Transactional
    public Long replyWrite(ReplyDto replyDto) {

        // 테이블에 게시글 id가 있는지 확인
        Optional<BoardEntity> boardEntity = boardRepository.findByBoardId(replyDto.getBoardId());

        if (boardEntity.isPresent()) {
            BoardEntity boardEntity1 = boardEntity.get();

            ReplyEntity replyEntity = ReplyEntity.toReplyEntity(replyDto, boardEntity1);

            // 작성자, 내용, 글번호에 해당하는 게시글
            return replyRepository.save(replyEntity).getReplyId();// id값 반환
        }

        return null;
    }

    // 댓글 목록
    public List<ReplyDto> replyList(Long boardId) {

        // 연관관계에 있는 게시글 -> BoardEntity
        BoardEntity boardEntity = boardRepository.findByBoardId(boardId).get();

        // 작성 댓글 게시글 목록
        List<ReplyEntity> boardReplyEntities = replyRepository.findAllByBoardEntityOrderByReplyIdDesc(boardEntity);

        List<ReplyDto> list = new ArrayList<>();

        for (ReplyEntity replyEntity : boardReplyEntities) {
            ReplyDto replyDto = ReplyDto.toReplyDto(replyEntity, boardId);
            list.add(replyDto);
        }
        return list;
    }

    // 댓글 수정
    @Transactional
    public Long replyUpdate(ReplyDto replyDto) {

        Optional<BoardEntity> boardEntity = boardRepository.findByBoardId(replyDto.getBoardId());

        if (boardEntity.isPresent()) {
            BoardEntity boardEntity1 = boardEntity.get();

            ReplyEntity replyEntity = ReplyEntity.toReplyUpdateEntity(replyDto, boardEntity1);

            return replyRepository.save(replyEntity).getReplyId();
        }
        return null;

    }

    // 댓글 삭제
    @Transactional
    public void replyDelete(Long replyId, Long boardId) {

        Optional<BoardEntity> boardEntity = boardRepository.findByBoardId(boardId);

        if (boardEntity.isPresent()) {

            Optional<ReplyEntity> replyEntity = replyRepository.findByReplyId(replyId);

            if (replyEntity.isPresent()) {
                replyRepository.deleteByReplyId(replyEntity.get().replyId);
            }
        }

    }
}
