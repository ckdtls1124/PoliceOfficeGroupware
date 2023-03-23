package org.project.groupware.service;

import lombok.RequiredArgsConstructor;
import org.project.groupware.dto.EventDto;
import org.project.groupware.entity.*;
import org.project.groupware.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EventService {

	private final EventRepository eventRepository;
	private final EventFileRepository eventFileRepository;
	private final EventGroupRepository eventGroupRepository;
	private final DeptRepository deptRepository;
	private final PoliceRepository policeRepository;
	private final PersonRepository personRepository;

	public static EventEntity eventDtoToEntityFile(EventDto eventDto) {
		//첨부된 파일이 있을 때
		EventEntity eventEntity = new EventEntity();

		eventEntity.setEventNumber(new Random().nextInt(1000000000));
		eventEntity.setEventLocation(eventDto.getEventLocation());
		eventEntity.setEventDate(eventDto.getEventDate());
		eventEntity.setEventSettle(eventDto.getEventSettle());
		eventEntity.setEventNote(eventDto.getEventNote());

		eventEntity.setEventJoinGroup(eventDto.getEventJoinGroup());
		eventEntity.setEventJoinDept(eventDto.getEventJoinDept());
		eventEntity.setEventJoinPolice(eventDto.getEventJoinPolice());
		eventEntity.setEventJoinPerson(eventDto.getEventJoinPerson());

		eventEntity.setEventAttachFile(1);

		return eventEntity;

	}

	public static EventEntity eventDtoToEntity(EventDto eventDto) {
		//첨부된 파일X
		EventEntity eventEntity = new EventEntity();

		eventEntity.setEventNumber(new Random().nextInt(1000000000));
		eventEntity.setEventLocation(eventDto.getEventLocation());
		eventEntity.setEventDate(eventDto.getEventDate());
		eventEntity.setEventSettle(eventDto.getEventSettle());
		eventEntity.setEventNote(eventDto.getEventNote());

		eventEntity.setEventJoinGroup(eventDto.getEventJoinGroup());
		eventEntity.setEventJoinDept(eventDto.getEventJoinDept());
		eventEntity.setEventJoinPolice(eventDto.getEventJoinPolice());
		eventEntity.setEventJoinPerson(eventDto.getEventJoinPerson());

		eventEntity.setEventAttachFile(0);

		return eventEntity;

	}

	public static EventFileEntity eventFileUpload(EventEntity eventEntity, String eventFileName) {

		EventFileEntity eventFileEntity = new EventFileEntity();

		eventFileEntity.setFileJoinEvent(eventEntity);
		eventFileEntity.setEventFileName(eventFileName);

		return eventFileEntity;

	}

	public static EventDto eventEntityToDto(EventEntity eventEntity) {

		EventDto eventDto = new EventDto();

		eventDto.setEventId(eventEntity.getEventId());
		eventDto.setEventNumber(eventEntity.getEventNumber());
		eventDto.setEventLocation(eventEntity.getEventLocation());
		eventDto.setEventDate(eventEntity.getEventDate());
		eventDto.setEventSettle(eventEntity.getEventSettle());
		eventDto.setEventNote(eventEntity.getEventNote());

		eventDto.setEventGroupName(eventEntity.getEventJoinGroup().getEventGroupName());
		eventDto.setDeptName(eventEntity.getEventJoinDept().getDeptName());
		eventDto.setPoliceName(eventEntity.getEventJoinPolice().getPoliceName());
		eventDto.setPersonName(eventEntity.getEventJoinPerson().getPersonName());

		if(eventEntity.getEventAttachFile()==0){
			eventDto.setEventAttachFile(eventDto.getEventAttachFile());
		}else {
			eventDto.setEventAttachFile(eventDto.getEventAttachFile());
			eventDto.setEventFileName(eventEntity.getEventFileEntities().get(0).getEventFileName());
		}

		return eventDto;
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
			eventRepository.save(eventDtoToEntity(eventDto));
		}else{
			//파일이 있을 때
			//1. 파일을 저장 장치(c:드라이브의 지정된 폴더)에 저장
			MultipartFile eventFile = eventDto.getEventFile();
			String fileName = eventFile.getOriginalFilename();
			String filePath = "c:/saveEventFiles" + fileName;
			eventFile.transferTo(new File(filePath));
			
			//2. 사건을 테이블에 저장
			EventEntity eventEntity = eventDtoToEntityFile(eventDto);
			//3. 사건의 id 정보를 get
			Long eventId = eventRepository.save(eventEntity).getEventId();

			//4. 사건의 id 정보를 조회해 사건을 가져온다
			Optional<EventEntity> eventFind = eventRepository.findById(eventId);
			EventEntity eventGet = eventFind.get();
			//5. 해당하는 사건의 파일 테이블에 정보 저장
			EventFileEntity file = eventFileUpload(eventGet, fileName);
			eventFileRepository.save(file);
		}

	}

	//사건 전체목록 불러오기
	public Page<EventDto> allEventsView(Pageable pageable) {

		Page<EventEntity> eventEntityList = eventRepository.findAll(pageable);
		Page<EventDto> eventDtoList = eventEntityList.map(EventService::eventEntityToDto);

		return eventDtoList;
	}

	//특정 사건 상세조회
	public EventDto eventDetailView(Long eventId) {

		Optional<EventEntity> optionalEventEntity = eventRepository.findById(eventId);

		if(optionalEventEntity.isPresent()){
			EventDto eventDto = eventEntityToDto(optionalEventEntity.get());
			return eventDto;
		}else {
			return null;
		}

	}

	@Transactional
	public void eventUpdateDo(Long eventId, EventDto eventDto) {

		EventEntity eventEntity = eventRepository.findById(eventId).get();
		eventEntity.setEventSettle(eventDto.getEventSettle());
		eventEntity.setEventNote(eventDto.getEventNote());

	}

	//사건 검색(날짜, 해결 여부)
	public List<EventDto> eventSearchDateOrSettle(EventDto eventDto) {

	}
}
