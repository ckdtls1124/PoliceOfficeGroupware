package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.entity.MemorandumFileEntity;
import org.spring.p21suck2jo.repository.MemorandumFileRepository;
import org.spring.p21suck2jo.service.MemorandumFileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/memo/file")
@RequiredArgsConstructor
public class MemorandumFileController {


    private final MemorandumFileService memorandumFileService;
    private final MemorandumFileRepository memorandumFileRepository;





//    첨부 파일 다운로드
    @GetMapping("/attach/{id}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long id) throws MalformedURLException{

        MemorandumFileEntity file = memorandumFileRepository.findById(id).orElse(null);
        UrlResource resource = new UrlResource("file:"+file.getMemorandumFilePath());
        String encodedFileName = UriUtils.encode(file.getMemorandumFileOriginalName(), StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\""+encodedFileName+"\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);

    }

//    저장한 파일 중 선택 삭제
    @GetMapping("/delete/{memorandumFileId}")
    public ResponseEntity<String> deleteSelectedFile(@PathVariable Long memorandumFileId){


        if(memorandumFileService.deleteSelectedFile(memorandumFileId) == 1){
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        return null;
    }


}
