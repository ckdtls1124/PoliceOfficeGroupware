package org.spring.p21suck2jo.memorandumService;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.spring.p21suck2jo.configuration.FileUploadProperties;
import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.MemorandumFileEntity;
import org.spring.p21suck2jo.exceptions.MemoFileDownloadException;
import org.spring.p21suck2jo.exceptions.MemoFileUploadException;
import org.spring.p21suck2jo.memorandumRepository.MemorandumFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemorandumFileService {
    @Value("${file.dir}")
    private static String dir;

    private final MemorandumFileRepository memorandumFileRepository;

    public Long putFileIntoDB(MultipartFile mulFile) throws IOException {
        if (mulFile.isEmpty()) {
            return null;
        }

        String origName = mulFile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String extension = origName.substring(origName.lastIndexOf("."));
        String savedName = uuid + extension;
        String savedPath = dir + savedName;

        MemorandumFileEntity memoFile = MemorandumFileEntity.builder()
                .memorandumFileOriginalName(origName)
                .memorandumFileSavedName(savedName)
                .memorandumFilePath(savedPath)
                .build();

        mulFile.transferTo(new File(savedPath));

        MemorandumFileEntity savedFile = memorandumFileRepository.save(memoFile);

        return savedFile.getMemorandumFileId();
    }


    public List<MemorandumFileEntity> findAllFiles(){
//        Memo 파일 번호를 받아서, 해당 번호에 맞는 파일만 찾도록 추후에 변경 필요

        return memorandumFileRepository.findAll();
    }
}
