package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.MemorandumDto;
import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.MemorandumFileEntity;
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

    public Long putFileIntoDB(MultipartFile mulFile, MemorandumDto memorandumDto, Long memorandumId) throws IOException {

//        작성한 결재 문서 찾기
        Optional<MemorandumEntity> memorandumEntity = memorandumRepository.findById(memorandumId);

//        작성한 결재 문서가 있으면 파일을 넣는다.
        if (memorandumEntity.isPresent()) {

//        파일 넣기
            if (mulFile.isEmpty()) {
                return null;
            } else{



            String origName = mulFile.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String extension = origName.substring(origName.lastIndexOf("."));
            String savedName = uuid + extension;
            String savedPath = uploadPath + savedName;

//        위에서 찾은 memorandumEntity를 넣는다.
            MemorandumFileEntity memoFile = MemorandumFileEntity.builder()
                    .memorandumFileOriginalName(origName)
                    .memorandumFileSavedName(savedName)
                    .memorandumFilePath(savedPath)
                    .memorandumEntity(memorandumEntity.get())
                    .build();

            mulFile.transferTo(new File(savedPath));

            MemorandumFileEntity savedFile = memorandumFileRepository.save(memoFile);

            return savedFile.getMemorandumFileId();
            }
        } else {
            return null;
        }
    }

    public List<MemorandumFileEntity> findAllFilesInSelectedMemo(Long memoId) {
//        Memo 파일 번호를 받아서, 해당 Entity에 맞는 file 전달하기
        List<MemorandumFileEntity> memorandumFileEntityList=memorandumFileRepository.findAllByMemorandumEntity(memoId);
        return memorandumFileEntityList;
    }

    public int deleteFilesInSelectedMemo(Long id) {

//        결재 문서를 찾는다.
        Optional<MemorandumEntity> memorandumEntity = memorandumRepository.findById(id);

//       찾은 결재 문서에 해당하는 파일을 찾는다.
        List<MemorandumFileEntity> selectedMemoFiles=memorandumFileRepository.findAllByMemorandumEntity(memorandumEntity.get().getMemorandumId());

        if (selectedMemoFiles != null) {
            for(MemorandumFileEntity fileEntity: selectedMemoFiles){
                memorandumFileRepository.deleteById(fileEntity.getMemorandumFileId());
            }
            return 1;
        } else {
            return 0;
        }
    }

}
