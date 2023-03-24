package org.project.groupware.service;

import lombok.RequiredArgsConstructor;
import org.project.groupware.constructor.EventConstructors;
import org.project.groupware.dto.EventDto;
import org.project.groupware.dto.EventGroupDto;
import org.project.groupware.entity.*;
import org.project.groupware.repository.*;
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


	public List<EventGroupDto> eventRegisterSelect() {

		List<EventGroupEntity> eventEntities = eventGroupRepository.findAll();
		List<EventGroupDto> eventGroupDto = new ArrayList<>();

		for(EventGroupEntity eventGroupEntity : eventEntities){
			eventGroupDto.add(EventConstructors.eventGroupEntityToDto(eventGroupEntity));
		}

		return eventGroupDto;
	}

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

		Page<EventEntity> eventSearchEntity = eventRepository.findEventSearch(pageable, startDate, endDate, eventSettle);
		Page<EventDto> eventSearchDto = eventSearchEntity.map(EventConstructors::eventEntityToDto);

		return eventSearchDto;
	}

}
