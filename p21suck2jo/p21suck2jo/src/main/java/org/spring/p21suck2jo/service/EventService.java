package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.constructor.EventConstructors;
import org.spring.p21suck2jo.dto.*;
import org.spring.p21suck2jo.entity.*;
import org.spring.p21suck2jo.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

	private final EventRepository eventRepository;
	private final EventFileRepository eventFileRepository;
	private final EventGroupRepository eventGroupRepository;
	private final DeptRepository deptRepository;
	private final PoliceRepository policeRepository;
	private final PersonRepository personRepository;

	public List<EventGroupDto> eventRegisterSelectGroup() {

		List<EventGroupEntity> eventEntities = eventGroupRepository.findAll();
		List<EventGroupDto> eventGroupDto = new ArrayList<>();

		for(EventGroupEntity eventGroupEntity : eventEntities){
			eventGroupDto.add(EventConstructors.eventGroupEntityToDto(eventGroupEntity));
		}
		return eventGroupDto;
	}

	public List<PoliceDto> eventRegisterSelectPolice() {
		List<PoliceEntity> policeEntities = policeRepository.findAll();
		List<PoliceDto> policeDto = new ArrayList<>();

		for(PoliceEntity policeEntity : policeEntities){
			policeDto.add(PoliceDto.officerView(policeEntity));
		}
		return policeDto;
	}

	public List<DeptDto> eventRegisterSelectDept() {
		List<DeptEntity> deptEntities = deptRepository.findAll();
		List<DeptDto> deptDto = new ArrayList<>();

		for(DeptEntity deptEntity : deptEntities){
			deptDto.add(DeptDto.deptView(deptEntity));
		}
		return deptDto;
	}

	public List<PersonDto> eventRegisterSelectPerson() {
		List<PersonEntity> personEntities = personRepository.findAll();
		List<PersonDto> personDto = new ArrayList<>();

		for(PersonEntity personEntity : personEntities){
			personDto.add(PersonDto.builder()
											.personId(personEntity.getPersonId())
											.personName(personEntity.getPersonName())
							.build());
		}
		return personDto;
	}

	//사건 등록
	@Transactional
	public void eventRegister(EventDto eventDto) throws IOException {

		Long eventGroupNo = eventDto.getEventGroup();
		EventGroupEntity eventGroupEntity = eventGroupRepository.findById(eventGroupNo).get();
		eventDto.setEventJoinGroup(eventGroupEntity);

		Long deptNo = eventDto.getDept();
		DeptEntity deptEntity = deptRepository.findById(deptNo).get();
		eventDto.setEventJoinDept(deptEntity);

		Long policeNo = eventDto.getPolice();
		PoliceEntity policeEntity = policeRepository.findById(policeNo).get();
		eventDto.setEventJoinPolice(policeEntity);

		Long personNo = eventDto.getPerson();
		PersonEntity personEntity = personRepository.findById(personNo).get();
		eventDto.setEventJoinPerson(personEntity);

		//파일업로드 처리
		if(eventDto.getEventFile().isEmpty()){
			//파일이 없을 때
			eventRepository.save(EventConstructors.eventDtoToEntity(eventDto));
		}else{
			//파일이 있을 때
			//1. 파일을 저장 장치(c:드라이브의 지정된 폴더)에 저장
			MultipartFile eventFile = eventDto.getEventFile();
			String fileName = eventFile.getOriginalFilename();
			String filePath = "C:/saveEventFiles/" + fileName;
			eventFile.transferTo(new File(filePath));

			//2. 사건을 테이블에 저장
			EventEntity eventEntity = EventConstructors.eventDtoToEntityFile(eventDto);
			//3. 사건의 id 정보를 get
			Long eventId = eventRepository.save(eventEntity).getEventId();

			//4. 사건의 id 정보를 조회해 사건을 가져온다
			Optional<EventEntity> eventFind = eventRepository.findById(eventId);
			EventEntity eventGet = eventFind.get();
			//5. 해당하는 사건의 파일 테이블에 정보 저장
			EventFileEntity file = EventConstructors.eventFileUpload(eventGet, fileName);
			eventFileRepository.save(file);
		}

	}

	//사건 전체목록 불러오기
	public Page<EventDto> allEventsView(Pageable pageable) {

		Page<EventEntity> eventEntityList = eventRepository.findAll(pageable);
		Page<EventDto> eventDtoList = eventEntityList.map(EventConstructors::eventEntityToDto);

		return eventDtoList;
	}

	//특정 사건 상세조회
	public EventDto eventDetailView(Long eventId) {

		Optional<EventEntity> optionalEventEntity = eventRepository.findById(eventId);

		if(optionalEventEntity.isPresent()){
			EventDto eventDto = EventConstructors.eventEntityToDto(optionalEventEntity.get());
			return eventDto;
		}else {
			return null;
		}

	}

	//사건 업데이트
	@Transactional
	public void eventUpdateDo(Long eventId, EventDto eventDto) {

		EventEntity eventEntity = eventRepository.findById(eventId).get();
		eventEntity.setEventSettle(eventDto.getEventSettle());
		eventEntity.setEventNote(eventDto.getEventNote());

	}

	//사건 검색(날짜, 해결 여부)
	public Page<EventDto> eventSearchDateOrSettle(Pageable pageable, String startDate, String endDate, Long eventSettle) {

		if(startDate.isEmpty() && endDate.isEmpty() && eventSettle!=null){
			//해결 여부만 체크했을 때
			Page<EventEntity> onlySettleSearch = eventRepository.findEventSettle(pageable, eventSettle);
			Page<EventDto> eventSearchDto = onlySettleSearch.map(EventConstructors::eventEntityToDto);
			return eventSearchDto;
		}else if (startDate!=null && endDate!=null && eventSettle==null){
			//날짜만 체크했을 때
			Page<EventEntity> onlyDateSearch = eventRepository.findEventDate(pageable, startDate, endDate);
			Page<EventDto> eventSearchDto = onlyDateSearch.map(EventConstructors::eventEntityToDto);
			return eventSearchDto;
		} else if (startDate!=null && endDate!=null && eventSettle!=null) {
			//날짜, 해결 여부 모두 체크했을 때
			Page<EventEntity> eventSearchAll = eventRepository.findEventSearch(pageable, startDate, endDate, eventSettle);
			Page<EventDto> eventSearchDto = eventSearchAll.map(EventConstructors::eventEntityToDto);
			return eventSearchDto;
		}else {
			return null;
		}
	}

	public Page<EventDto> myEventView(Pageable pageable, String nowPolice) {

		//policeId 뽑음
		Long policeId = eventRepository.findByEmail(nowPolice);

		//해당하는 policeId를 가지고있는 event 목록을 뽑음
		Page<EventEntity> myEventEntities = eventRepository.findMyEvent(pageable, policeId);
		Page<EventDto> myEventDto = myEventEntities.map(EventConstructors::eventEntityToDto);

		return myEventDto;
	}
}
