package org.spring.p21suck2jo.memorandumController;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import org.spring.p21suck2jo.entity.MemorandumFileEntity;
import org.spring.p21suck2jo.memorandumRepository.MemorandumFileRepository;
import org.spring.p21suck2jo.memorandumService.MemorandumFileService;
import org.spring.p21suck2jo.memorandumService.MemorandumService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

@Controller
@RequiredArgsConstructor
public class MemorandumController {

    private final MemorandumService memorandumService;
    private final MemorandumFileService memorandumFileService;
    private final MemorandumFileRepository memorandumFileRepository;

    @GetMapping("/")
    public String memorandumPage(Model model) {
        try{
            List<MemorandumFileEntity> memorandumFileEntities = memorandumFileService.findAllFiles();
            model.addAttribute("files", memorandumFileEntities);
        } catch (Exception e){
            System.out.println("No files found");
        }

        return "FilePage";
    }

//    파일 첨부하기

    @PostMapping("/uploadFileInMemo")
    public String uploadFile(@RequestParam("file") List<MultipartFile> files) throws IOException {
        for (MultipartFile multipartFile : files) {
            memorandumFileService.putFileIntoDB(multipartFile);
        }

        return "redirect:/";
    }

//    첨부 파일 다운로드
    @GetMapping("/attach/{id}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long id) throws MalformedURLException{

        MemorandumFileEntity file = memorandumFileRepository.findById(id).orElse(null);

        UrlResource resource = new UrlResource("file:"+file.getMemorandumFilePath());
        System.out.println("Resource :"+resource);

        String encodedFileName = UriUtils.encode(file.getMemorandumFileOriginalName(), StandardCharsets.UTF_8);
        System.out.println("Encoded file name :"+encodedFileName);

        String contentDisposition = "attachment; filename=\""+encodedFileName+"\"";
        System.out.println("Content Disposition :"+contentDisposition);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);

    }

//    저장한 파일 중 선택 삭제
    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteSelectedFile(@PathVariable Long id){
        if(memorandumFileService.deleteSelectedFile(id) == 1){
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            return null;
        }
    }


}
