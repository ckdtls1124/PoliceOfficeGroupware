package org.spring.p21suck2jo.memorandumService;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.spring.p21suck2jo.configuration.FileUploadProperties;
import org.spring.p21suck2jo.dto.MemorandumFileDto;
import org.spring.p21suck2jo.entity.MemorandumEntity;
import org.spring.p21suck2jo.entity.MemorandumFileEntity;
import org.spring.p21suck2jo.exceptions.MemoFileDownloadException;
import org.spring.p21suck2jo.exceptions.MemoFileUploadException;
import org.spring.p21suck2jo.memorandumRepository.MemorandumFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class MemorandumFileService {

    private final MemorandumFileRepository memorandumFileRepository;

    public void putFileIntoDB(Path fileData) {

        MemorandumFileDto memorandumFileDto = MemorandumFileDto.builder()
                .memorandumFileName(fileData.getFileName().toString())
                .memorandumFileUri(fileData.toFile().toURI().toString())
                .build();
        memorandumFileRepository.save(new MemorandumFileEntity(memorandumFileDto.getMemorandumFileName(), memorandumFileDto.getMemoFileData()));

    }
}
