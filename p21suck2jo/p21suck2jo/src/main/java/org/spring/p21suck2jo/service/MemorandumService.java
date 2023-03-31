package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.MemorandumDto;
import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.MemorandumRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemorandumService {
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;
    private final MemorandumRepository memorandumRepository;



//    나의 결재함(검색어가 없을 경우)
    public Page<MemorandumDto> findAllMemo(Long policeId, Pageable pageable) {

        PoliceEntity policeEntity = new PoliceEntity();
        policeEntity.setPoliceId(policeId);


        //List<MemorandumEntity> memoEntity=memorandumRepository.findAllByPolice(policeEntity);

        Page<MemorandumEntity> memorandumEntityPage = memorandumRepository.findByPolice(policeEntity, pageable);
        Page<MemorandumDto> memorandumDtos = memorandumEntityPage.map(MemorandumDto :: toMemorandumDto);

        return memorandumDtos;

    }

//    나의 결재함(검색어가 있을 경우)
    public Page<MemorandumDto> memoListSearchPage(String search, Pageable pageable) {
        Page<MemorandumEntity> memorandumEntityPage = memorandumRepository.findByMemorandumTitleContaining(search, pageable);

        Page<MemorandumDto> memorandumDtoPage = memorandumEntityPage.map(MemorandumDto::toMemorandumDto);

        return memorandumDtoPage;
    }

//    결재 문서 작성
    public Long writeMemorandum(MemorandumDto memorandumDto, Long sessionPoliceId) throws IOException {

        PoliceEntity policeEntity = new PoliceEntity();
        policeEntity.setPoliceId(sessionPoliceId);

        MemorandumEntity memorandum = MemorandumEntity.builder()
                .memorandumTitle(memorandumDto.getMemorandumTitle())
                .memorandumContent(memorandumDto.getMemorandumContent())
                .approval(0)
                .police(policeEntity)
                .approvingMemberList(memorandumDto.getApprovingMemberList())
                .build();

        Long memorandumId = memorandumRepository.save(memorandum).getMemorandumId();

        return memorandumId;

    }

//    결재 문서 수정
    public void updateMemorandum(MemorandumEntity memorandumEntity, Long sessionPoliceId) {
        PoliceEntity policeEntity = new PoliceEntity();
        policeEntity.setPoliceId(sessionPoliceId);

        memorandumEntity.setPolice(policeEntity);

        memorandumRepository.save(memorandumEntity);
    }


//    결재 문서 상세 보기
    public MemorandumEntity findSelectedMemo(Long memoId) {
        Optional<MemorandumEntity> memoEntity= memorandumRepository.findById(memoId);

        return memoEntity.get();
    }

//    결재 문서 삭제
    public void deleteSelectedMemo(Long id) {

        memorandumRepository.deleteById(id);

    }


}
