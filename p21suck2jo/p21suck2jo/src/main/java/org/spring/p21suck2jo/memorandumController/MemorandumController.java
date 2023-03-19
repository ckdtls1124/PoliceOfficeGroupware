package org.spring.p21suck2jo.memorandumController;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.memorandumService.MemorandumFileService;
import org.spring.p21suck2jo.memorandumService.MemorandumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequiredArgsConstructor
public class MemorandumController {

    private final MemorandumService memorandumService;
    private final MemorandumFileService memorandumFileService;

    @GetMapping("/")
    public String memorandumPage(){
        return "FilePage";
    }


}
