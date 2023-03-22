package org.project.groupware.service;

import lombok.RequiredArgsConstructor;
import org.project.groupware.dto.EventDto;
import org.project.groupware.entity.EventEntity;
import org.project.groupware.entity.EventFileEntity;
import org.project.groupware.entity.EventGroupEntity;
import org.project.groupware.repository.EventFileRepository;
import org.project.groupware.repository.EventGroupRepository;
import org.project.groupware.repository.EventRepository;
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

	@Transactional
	public void eventRegister(EventDto eventDto) throws IOException {

		Long eventGroupNo = eventDto.getEventGroup();
		Optional<EventGroupEntity> eventGroup = eventGroupRepository.findById(eventGroupNo);
		EventGroupEntity eventGroupEntity = eventGroup.get();

		eventDto.setEventJoinGroup(eventGroupEntity);

		//파일업로드 처리
		if(eventDto.getEventFile().isEmpty()){
			//파일이 없을 때
			eventRepository.save(EventEntity.eventDtoToEntity(eventDto));
		}else{
			//파일이 있을 때
			//1. 파일을 저장 장치(c:드라이브의 지정된 폴더)에 저장
			MultipartFile eventFile = eventDto.getEventFile();
			String fileName = eventFile.getOriginalFilename();
			String filePath = "c:/saveEventFiles" + fileName;
			eventFile.transferTo(new File(filePath));
			
			//2. 사건을 테이블에 저장
			EventEntity eventEntity = EventEntity.eventDtoToEntityFile(eventDto);
			//3. 사건의 id 정보를 get
			Long eventId = eventRepository.save(eventEntity).getEventId();

			//4. 사건의 id 정보를 조회해 사건을 가져온다
			Optional<EventEntity> eventFind = eventRepository.findById(eventId);
			EventEntity eventGet = eventFind.get();
			//5. 해당하는 사건의 파일 테이블에 정보 저장
			EventFileEntity file = EventFileEntity.eventFileUpload(eventGet, fileName);
			eventFileRepository.save(file);
		}

	}

	//사건 전체목록 불러오기
	public Page<EventDto> allEventsView(Pageable pageable) {

		Page<EventEntity> eventEntityList = eventRepository.findAll(pageable);
		Page<EventDto> eventDtoList = eventEntityList.map(EventDto::eventEntityToDto);

		return eventDtoList;
	}

	//특정 사건 상세조회
	public EventDto eventDetailView(Long eventId) {

		Optional<EventEntity> optionalEventEntity = eventRepository.findById(eventId);

		if(optionalEventEntity.isPresent()){
			EventDto eventDto = EventDto.eventEntityToDto(optionalEventEntity.get());
			return eventDto;
		}else {
			return null;
		}

	}
}
