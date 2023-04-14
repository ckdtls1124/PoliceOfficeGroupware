package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.ReplyDto;
import org.spring.p21suck2jo.entity.BoardEntity;
import org.spring.p21suck2jo.entity.ReplyEntity;
import org.spring.p21suck2jo.repository.ReplyRepository;
import org.spring.p21suck2jo.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;


    @Transactional
    public Long insertReply(ReplyDto replyDto) {

        Optional<BoardEntity> boardEntity=boardRepository.findByBoardId(replyDto.getBoardId());

        if(boardEntity.isPresent()){
            BoardEntity boardEntity1=boardEntity.get();

            ReplyEntity replyEntity = ReplyEntity.toReplyEntity(replyDto,boardEntity1);

            return replyRepository.save(replyEntity).getReplyId();
        }else{
            return null;
        }
    }

    public List<ReplyDto> replyList(Long boardId) {

        BoardEntity boardEntity=boardRepository.findByBoardId(boardId).get();

        List<ReplyEntity> boardReplyEntities= replyRepository.findAllByBoardEntityOrderByReplyIdDesc(boardEntity);

        List<ReplyDto> list =new ArrayList<>();

        for(ReplyEntity replyEntity:boardReplyEntities){
            ReplyDto replyDto= ReplyDto.toReplyDto(replyEntity,boardId);
            list.add(replyDto);
        }
        return list;
    }
    @Transactional
    public Long updateReply(ReplyDto replyDto) {

        Optional<BoardEntity> boardEntity=boardRepository.findByBoardId(replyDto.getBoardId());

        if(boardEntity.isPresent()){
            BoardEntity boardEntity1=boardEntity.get();

            ReplyEntity replyEntity = ReplyEntity.toReplyUpdateEntity(replyDto,boardEntity1);

            return replyRepository.save(replyEntity).getReplyId();
        }else{
            return null;
        }
    }

    @Transactional
    public void replyDelete(Long replyId,Long boardId) {

        Optional<BoardEntity> boardEntity=boardRepository.findByBoardId(boardId);

        if(boardEntity.isPresent()){

            Optional<ReplyEntity> replyEntity=replyRepository.findByReplyId(replyId);

            if(replyEntity.isPresent()){

                replyRepository.deleteByReplyId(replyEntity.get().replyId);
            }
        }

    }
}
