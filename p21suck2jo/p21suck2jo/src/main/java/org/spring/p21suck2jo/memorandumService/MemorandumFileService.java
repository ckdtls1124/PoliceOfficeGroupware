package org.spring.p21suck2jo.memorandumService;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.entity.MemorandumFileEntity;
import org.spring.p21suck2jo.memorandumRepository.MemorandumFileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemorandumFileService {

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    private final MemorandumFileRepository memorandumFileRepository;

    public Long putFileIntoDB(MultipartFile mulFile) throws IOException {
        if (mulFile.isEmpty()) {
            return null;
        }


        String origName = mulFile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String extension = origName.substring(origName.lastIndexOf("."));
        String savedName = uuid + extension;
        String savedPath = uploadPath + savedName;

        MemorandumFileEntity memoFile = MemorandumFileEntity.builder()
                .memorandumFileOriginalName(origName)
                .memorandumFileSavedName(savedName)
                .memorandumFilePath(savedPath)
                .build();

        mulFile.transferTo(new File(savedPath));

        MemorandumFileEntity savedFile = memorandumFileRepository.save(memoFile);

        return savedFile.getMemorandumFileId();
    }


    public List<MemorandumFileEntity> findAllFiles() {
//        Memo 파일 번호를 받아서, 해당 번호에 맞는 파일만 찾도록 추후에 변경 필요

        return memorandumFileRepository.findAll();
    }

    public int deleteSelectedFile(Long id) {
        if (memorandumFileRepository.findById(id) != null) {
            memorandumFileRepository.deleteById(id);
            return 1;
        } else {
            return 0;
        }
    }

}
