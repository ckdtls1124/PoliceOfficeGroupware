package org.project.groupware.controller;

import lombok.RequiredArgsConstructor;
import org.project.groupware.dto.EventDto;
import org.project.groupware.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

	private final EventService eventService;

	//메인페이지(사건사고 목록)
	@GetMapping({"/", "/list"})
	public String eventList() {
		//* 페이징 적용하기
		return "event/eventMain";
	}


	//사건 등록 페이지로 이동
	@GetMapping("/register")
	public String eventWriteView(EventDto eventDto) {
		return "event/eventRegister";
	}

	//사건 등록
	@PostMapping("/register")
	public String eventWriteDo(@Valid BindingResult bindingResult, EventDto eventDto) throws IOException {

		//유효성처리
		if(bindingResult.hasErrors()){
			//유효성을 만족하지 못하면 작성 페이지에 계속 머무른다
			return "event/eventRegister";
		} else{
			//유효성 통과 후 작성
			eventService.eventRegister(eventDto);
			//작성 후 메인페이지로 이동
			return "redirect:/event/";
		}

	}



}
