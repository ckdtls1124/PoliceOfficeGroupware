package org.spring.p21suck2jo.controller;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.*;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

	private final EventService eventService;

	//메인페이지(사건사고 목록)
	@GetMapping({"/", "/list", "/main"})
	public String eventMain(Model model, @PageableDefault(page = 0, size = 10, sort = "eventId", direction = Sort.Direction.DESC) Pageable pageable) {

		Page<EventDto> eventMainView = eventService.allEventsView(pageable);

		int block = 5;
		int nowPage = eventMainView.getNumber() + 1;
		int startPage = Math.max(1, eventMainView.getNumber() - block);
		int endPage = eventMainView.getTotalPages();
		int totalPage = eventMainView.getTotalPages();

		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalPage", totalPage);

		model.addAttribute("eventMainView", eventMainView);

		return "event/eventMain";
	}


	//사건 등록 페이지로 이동
	@GetMapping("/register")
	public String eventWriteView(Model model) {

		List<EventGroupDto> eventGroupDto = eventService.eventRegisterSelectGroup();
		List<PoliceDto> eventPoliceDto = eventService.eventRegisterSelectPolice();
		List<DeptDto> eventDeptDto = eventService.eventRegisterSelectDept();
		List<PersonDto> eventPersonDto = eventService.eventRegisterSelectPerson();

		model.addAttribute("eventDto", new EventDto());

		model.addAttribute("eventGroup", eventGroupDto);
		model.addAttribute("police", eventPoliceDto);
		model.addAttribute("dept", eventDeptDto);
		model.addAttribute("person", eventPersonDto);

		return "event/eventRegister";
	}

	//사건 등록 실행
	@PostMapping("/register")
	public String eventWriteDo(@Valid EventDto eventDto, BindingResult bindingResult) throws IOException {

		//유효성처리
		if(bindingResult.hasErrors()){
			return "event/eventRegister";
		}

		eventService.eventRegister(eventDto);
		return "redirect:/event/";
	}

	//사건 상세조회
	@GetMapping("/detail/{eventId}")
	public String eventDetailView(@PathVariable Long eventId, Model model) {

		EventDto eventDetailDto = eventService.eventDetailView(eventId);
		model.addAttribute("detail", eventDetailDto);

		return "event/eventDetail";
	}

	//사건 업데이트 페이지로 이동
	@GetMapping("/update/{eventId}")
	public String eventUpdateView(@PathVariable Long eventId, Model model){

		EventDto eventDetailDto = eventService.eventDetailView(eventId);
		model.addAttribute("detail", eventDetailDto);

		return "event/eventUpdate";
	}

	//사건 업데이트 실행
	@PostMapping("/update/{eventId}")
	public String eventUpdateDo(@PathVariable Long eventId, EventDto eventDto){

		eventService.eventUpdateDo(eventId, eventDto);

		return "redirect:/event/detail/{eventId}";
	}

	//사건 검색(날짜, 해결 여부)
	@GetMapping("/search")
	public String eventSearchDo(@RequestParam(value = "startDate", required = false)String startDate,
															@RequestParam(value = "endDate", required = false)String endDate,
															@RequestParam(value = "eventSettle", required = false)Long eventSettle,
															@PageableDefault(page = 0, size = 10, sort = "event_id", direction = Sort.Direction.DESC) Pageable pageable,
															Model model){

		if(startDate.isEmpty() && endDate.isEmpty() && eventSettle==null){
			return "redirect:/event/";
		}

		Page<EventDto> eventSearchView = eventService.eventSearchDateOrSettle(pageable, startDate, endDate, eventSettle);

		int block = 5;
		int nowPage = eventSearchView.getNumber() + 1;
		int startPage = Math.max(1, eventSearchView.getNumber() - block);
		int endPage = eventSearchView.getTotalPages();
		int totalPage = eventSearchView.getTotalPages();

		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalPage", totalPage);

		model.addAttribute("eventMainView", eventSearchView);

		return "event/eventMain";
	}

	@GetMapping("/myevent")
	public String myEventView(@PageableDefault(page = 0, size = 10, sort = "event_id", direction = Sort.Direction.DESC) Pageable pageable,
														@AuthenticationPrincipal UserDetails user, Model model){

		String nowPolice = user.getUsername();

		Page<EventDto> myEventView = eventService.myEventView(pageable, nowPolice);

		int block = 5;
		int nowPage = myEventView.getNumber() + 1;
		int startPage = Math.max(1, myEventView.getNumber() - block);
		int endPage = myEventView.getTotalPages();
		int totalPage = myEventView.getTotalPages();

		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalPage", totalPage);

		model.addAttribute("eventMainView", myEventView);

		return "event/eventMain";
	}

}
