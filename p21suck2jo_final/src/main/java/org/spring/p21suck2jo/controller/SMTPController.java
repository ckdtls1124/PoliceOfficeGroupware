package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.dto.ResponseDto;
import org.spring.p21suck2jo.repository.PoliceRepository;
import org.spring.p21suck2jo.service.PoliceLoginService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
public class SMTPController {

    private final PoliceRepository policeRepository;
    private final PoliceLoginService PoliceLoginService;

    @PostMapping("/smtppwSearch")
    public ResponseDto<?> find(@RequestBody PoliceDto dto) {

        if(!policeRepository.existsByPoliceNumber(dto.getPoliceNumber()) || !Pattern.matches("^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", dto.getEmail())) {
            Map<String, String> validResult = new HashMap<>();

            if(!policeRepository.existsByPoliceNumber(dto.getPoliceNumber())) {
                validResult.put("policeNumber", "존재하지 않는 사원번호입니다.");
            }
            if(!Pattern.matches("^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", dto.getEmail())) {
                validResult.put("email", "올바르지 않은 이메일 형식입니다.");
            }
            return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), validResult);
        }
        PoliceLoginService.sendTmpPwd(dto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


}