package org.spring.p21suck2jo.memorandumController;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import org.spring.p21suck2jo.entity.MemorandumFileEntity;
import org.spring.p21suck2jo.memorandumRepository.MemorandumFileRepository;
import org.spring.p21suck2jo.memorandumService.MemorandumFileService;
import org.spring.p21suck2jo.memorandumService.MemorandumService;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.annotation.Resource;
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

    @PostMapping("/uploadFileInMemo")
    public String uploadFile(@RequestParam("file") List<MultipartFile> files) throws IOException {
        for (MultipartFile multipartFile : files) {
            memorandumFileService.putFileIntoDB(multipartFile);
        }

        return "redirect:/";
    }

//    첨부 파일 다운로드
    @GetMapping("/attach/{id}")
    public ResponseEntity<UrlResource> downloadAttach(@PathVariable Long id) throws MalformedURLException{

        MemorandumFileEntity file = memorandumFileRepository.findById(id).orElse(null);

        UrlResource resource = new UrlResource("file:"+file.getMemorandumFilePath());

        String encodedFileName = UriUtils.encode(file.getMemorandumFileOriginalName(), StandardCharsets.UTF_8);

        String contentDisposition = "attachment; filename=\""+encodedFileName+"\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);

    }


}
