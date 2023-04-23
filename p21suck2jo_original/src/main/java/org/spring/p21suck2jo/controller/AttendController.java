package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.entity.AttendEntity;
import org.spring.p21suck2jo.service.AttendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/attend")
@RequiredArgsConstructor
public class AttendController {

    private final AttendService attendService;

    //    업무 시작을 클릭하면, 로그인한 경찰관의 ID, 클릭이 발생한 시간이 들어간다.
    @GetMapping("/startWorking")
    public ResponseEntity<LocalDateTime> startWorking(HttpSession currentSession) {

        //        Long으로 변환된 Session의 경찰 아이디는 근태 테이블에 주입된다.
        Long sessionPoliceIdLong = Long.valueOf(String.valueOf(currentSession.getAttribute("currentPoliceId")));

//        클릭 시, 업무를 시작한 시간
        LocalDateTime localDateTime = LocalDateTime.now();

        attendService.startWorking(localDateTime, sessionPoliceIdLong);

        return new ResponseEntity<>(localDateTime, HttpStatus.OK);

    }

    //      업무 종료를 클릭하면, 이전에 입력된 row를 찾아서 종료시간만 Update 한다.
    @GetMapping("/endWorking")
    public ResponseEntity<LocalDateTime> endWorking(HttpSession currentSession) {

        //        Long으로 변환된 Session의 경찰 아이디는 근태 테이블에 주입된다.
        Long sessionPoliceIdLong = Long.valueOf(String.valueOf(currentSession.getAttribute("currentPoliceId")));
        //        클릭 시, 업무를 종료한 시간
        LocalDateTime localDateTime = LocalDateTime.now();

        AttendEntity attendEntity=attendService.findByPolice(sessionPoliceIdLong);
        attendEntity.setLeaveTime(localDateTime);

        attendService.updateEndWorkingTime(attendEntity);

        return new ResponseEntity<>(localDateTime, HttpStatus.OK);

    }


}
