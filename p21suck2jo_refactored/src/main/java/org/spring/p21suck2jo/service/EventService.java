package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.constructor.EventConstructors;
import org.spring.p21suck2jo.constructor.PoliceConstructors;
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


	//사건을 등록할 때 사건 분류를 선택할 수 있도록 분류 리스트를 등록 페이지로 가져간다
	public List<EventGroupDto> eventRegisterSelectGroup() {

		List<EventGroupEntity> eventEntities = eventGroupRepository.findAll();
		List<EventGroupDto> eventGroupDto = new ArrayList<>();

		for(EventGroupEntity eventGroupEntity : eventEntities){
			eventGroupDto.add(EventConstructors.eventGroupEntityToDto(eventGroupEntity));
		}
		return eventGroupDto;

	}

	//사건을 등록할 때 관련 시민을 선택할 수 있도록 시민 리스트를 등록 페이지로 가져간다
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

	//현재 로그인한 경찰의 정보가 사건의 등록 정보와 매핑되도록 처리
	public PoliceDto eventRegisterPolice(String nowPolice) {

		Optional<PoliceEntity> getPolice = policeRepository.findByEmail(nowPolice);
		PoliceDto policeDto = PoliceConstructors.entityToDto(getPolice.get());

		return policeDto;

	}

	//사건 등록 수행 메서드1
	//연관 관계를 맺은 테이블의 정보를 가져와 외래키로 저장
	@Transactional
	public EventDto eventRegister(EventDto eventDto) throws IOException {

		EventGroupEntity eventGroupEntity = eventGroupRepository.findById(eventDto.getEventGroup()).get();
		eventDto.setEventJoinGroup(eventGroupEntity);

		PoliceEntity policeEntity = policeRepository.findById(eventDto.getPolice()).get();
		eventDto.setEventJoinPolice(policeEntity);

		DeptEntity deptEntity = deptRepository.findById(eventDto.getDept()).get();
		eventDto.setEventJoinDept(deptEntity);

		PersonEntity personEntity = personRepository.findById(eventDto.getPerson()).get();
		eventDto.setEventJoinPerson(personEntity);

		//외래키들만 저장하고 최종적으로 글을 저장할 메서드로 리턴
		return eventFinalRegister(eventDto);

	}

	//사건 등록 수행 메서드2
	//파일 업로드 처리 후 최종적으로 사건을 저장
	@Transactional
	public EventDto eventFinalRegister(EventDto eventDto) throws IOException {

		if(eventDto.getEventFile().isEmpty()){
			//첨부된 파일이 없을 때(사건만 저장한다)
			eventRepository.save(EventConstructors.eventDtoToEntity(eventDto));
		}else{
			//첨부된 파일이 있을 때
			//1. 파일을 저장 장치(c:드라이브의 지정된 폴더)에 저장
			MultipartFile eventFile = eventDto.getEventFile();
			String fileName = eventFile.getOriginalFilename();
			String filePath = "C:/saveEventFiles/" + fileName;
			eventFile.transferTo(new File(filePath));

			//2. 사건을 테이블에 저장
			EventEntity eventEntity = EventConstructors.eventDtoToEntity(eventDto);
			//3. 사건의 id 정보를 get
			Long eventId = eventRepository.save(eventEntity).getEventId();

			//4. 사건의 id 정보를 조회해 사건을 가져온다
			Optional<EventEntity> eventFind = eventRepository.findById(eventId);
			EventEntity eventGet = eventFind.get();
			//5. 해당하는 사건의 파일 테이블에 정보 저장
			EventFileEntity file = EventConstructors.eventFileUpload(eventGet, fileName);
			eventFileRepository.save(file);
		}
		return eventDto;

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
		EventDto eventDto = EventConstructors.eventEntityToDto(optionalEventEntity.get());

		return eventDto;

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

		Page<EventDto> eventSearchDto = null;

		if(startDate.isEmpty() && endDate.isEmpty() && eventSettle!=null){
			//해결 여부만 체크했을 때
			Page<EventEntity> onlySettleSearch = eventRepository.findEventSettle(pageable, eventSettle);
			eventSearchDto = onlySettleSearch.map(EventConstructors::eventEntityToDto);
		}else if (startDate!=null && endDate!=null && eventSettle==null){
			//날짜만 체크했을 때
			Page<EventEntity> onlyDateSearch = eventRepository.findEventDate(pageable, startDate, endDate);
			eventSearchDto = onlyDateSearch.map(EventConstructors::eventEntityToDto);
		} else if (startDate!=null && endDate!=null && eventSettle!=null) {
			//날짜, 해결 여부 모두 체크했을 때
			Page<EventEntity> eventSearchAll = eventRepository.findEventSearch(pageable, startDate, endDate, eventSettle);
			eventSearchDto = eventSearchAll.map(EventConstructors::eventEntityToDto);
		}
		return eventSearchDto;

	}

	//현재 로그인한 경찰이 담당한 사건만을 조회(나의 사건)
	public Page<EventDto> myEventView(Pageable pageable, String nowPolice) {

		//이메일을 기준으로 해당하는 경찰관의 정보를 뽑는다
		Optional<PoliceEntity> policeId = policeRepository.findByEmail(nowPolice);

		//해당 경찰관의 기본키(policeId)를 외래키로 가지고있는 사건 목록을 뽑는다
		Page<EventEntity> myEventEntities = eventRepository.findMyEvent(pageable, policeId.get().getPoliceId());
		Page<EventDto> myEventDto = myEventEntities.map(EventConstructors::eventEntityToDto);

		return myEventDto;

	}

	//'오늘' 등록된 사건 조회
	public List<EventDto> todayEvent() {

		List<EventEntity> eventEntityList = eventRepository.findTodayEvent();
		List<EventDto> todayEventDto = new ArrayList<>();

		for(EventEntity eventEntity : eventEntityList){
			todayEventDto.add(EventConstructors.eventEntityToDto(eventEntity));
		}
		return todayEventDto;

	}

}
