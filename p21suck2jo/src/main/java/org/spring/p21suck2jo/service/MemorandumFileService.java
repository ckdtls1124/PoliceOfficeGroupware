package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.MemorandumDto;
import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.MemorandumFileEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.MemorandumFileRepository;
import org.spring.p21suck2jo.repository.MemorandumRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemorandumFileService {

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    private final MemorandumFileRepository memorandumFileRepository;
    private final MemorandumRepository memorandumRepository;


//   조회============================================
    public List<MemorandumFileEntity> findAllFilesInSelectedMemo(Long memoId) {
//        결재 문서를 찾는다.
        Optional<MemorandumEntity> memorandumEntity = memorandumRepository.findById(memoId);

//       찾은 결재 문서에 해당하는 파일을 찾는다.
        List<MemorandumFileEntity> memorandumFileEntityList = memorandumFileRepository.findAllByMemorandumEntity(memorandumEntity.get());
        return memorandumFileEntityList;
    }
//   조회============================================


//   작성============================================
    public Long putFileIntoDB(MultipartFile mulFile, MemorandumDto memorandumDto, Long memorandumId, Long sessionPoliceIdLong) throws IOException {

//        작성한 결재 문서 찾기
        Optional<MemorandumEntity> memorandumEntity = memorandumRepository.findById(memorandumId);

//        작성한 결재 문서가 있으면 파일을 넣는다.
        if (memorandumEntity.isPresent()) {

//        파일 넣기
            if (mulFile.isEmpty()) {
                return null;
            } else {
                String origName = mulFile.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                String extension = origName.substring(origName.lastIndexOf("."));
                String savedName = uuid + extension;
                String savedPath = uploadPath + savedName;

//            경찰 Entity 넣기
//                현재 Session 값을 Long 타입으로 변환하여, 해당 값을 가지고 있는 Police Entity를 넣는다.
                PoliceEntity policeEntity = new PoliceEntity();
                policeEntity.setPoliceId(sessionPoliceIdLong);


//        위에서 찾은 memorandumEntity를 넣는다.
                MemorandumFileEntity memoFile = MemorandumFileEntity.builder()
                        .memorandumFileOriginalName(origName)
                        .memorandumFileSavedName(savedName)
                        .memorandumFilePath(savedPath)
                        .memorandumEntity(memorandumEntity.get())
                        .police(policeEntity)
                        .build();

                mulFile.transferTo(new File(savedPath));

                MemorandumFileEntity savedFile = memorandumFileRepository.save(memoFile);

                return savedFile.getMemorandumFileId();
            }
        } else {
            return null;
        }
    }
//   작성============================================


//   삭제============================================
//    결재 문서를 삭제할 때, 해당 결제 문서의 파일을 전체 삭제
    public void deleteFilesInSelectedMemo(Long memoId) {
                MemorandumEntity memorandumEntity = new MemorandumEntity();
                memorandumEntity.setMemorandumId(memoId);
                memorandumFileRepository.deleteByMemorandumEntity(memorandumEntity);

    }

//    결재 문서에서 선택한 파일 삭제
    public int deleteSelectedFile(Long memoFileId) {

        if(memorandumFileRepository.findById(memoFileId) != null){
            memorandumFileRepository.deleteById(memoFileId);
            return 1;
        } else {
            return 0;
        }
    }
//   삭제============================================


}
