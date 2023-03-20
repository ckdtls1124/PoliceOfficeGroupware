package org.spring.p21suck2jo.memorandumController;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.memorandumService.MemorandumFileService;
import org.spring.p21suck2jo.memorandumService.MemorandumService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
public class MemorandumRestController {
    private final MemorandumService memorandumService;
    private final MemorandumFileService memorandumFileService;


    //    @PostMapping("/writeMemorandum")
//    public Path mkFile(@RequestParam("file") MultipartFile file) {
//        Path fileData = Paths.get(file.getOriginalFilename());
//
//        memorandumFileService.putFileIntoDB(fileData);
//        System.out.println("This is the original name of the inserted file :"+file.getOriginalFilename());
//        System.out.println("This shows the URI of the inserted file :"+fileData.toFile().toURI());
//
//
//        return fileData;
//    }
//    @PostMapping("/findFile")
//    public Path downloadFile(@RequestParam("fileData") String fileUrl) {
//        Path downloadFile = Paths.get(fileUrl);
//
////        다운로드 시, 파일이 저장되는 위치를 각 사용자의 '다운로드' 경로로 설정
//        String downloadPath ="C:\\Users\\Administrator\\Downloads";
//
//
//        System.out.println("This is file url in web page :"+fileUrl);
//
//        return downloadFile;
//    }

}
