package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.MemorandumDto;
import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.MemorandumRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemorandumService {
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;
    private final MemorandumRepository memorandumRepository;



//    나의 결재함
    public List<MemorandumEntity> findAllMemo(Long policeId) {

        PoliceEntity policeEntity = new PoliceEntity();
        policeEntity.setPoliceId(policeId);


        List<MemorandumEntity> memoEntity=memorandumRepository.findAllByPolice(policeEntity);

        return memoEntity;

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
