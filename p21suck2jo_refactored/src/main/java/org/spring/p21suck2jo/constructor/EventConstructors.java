package org.spring.p21suck2jo.constructor;

import org.spring.p21suck2jo.dto.EventDto;
import org.spring.p21suck2jo.dto.EventGroupDto;
import org.spring.p21suck2jo.entity.EventEntity;
import org.spring.p21suck2jo.entity.EventFileEntity;
import org.spring.p21suck2jo.entity.EventGroupEntity;

import java.util.Random;

public class EventConstructors {

	//사건 Dto -> Entity
	//글 저장 시 사용
	public static EventEntity eventDtoToEntity(EventDto eventDto) {

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

		if(eventDto.getEventFile().isEmpty()){
			eventEntity.setEventAttachFile(0);
		}else{
			eventEntity.setEventAttachFile(1);
		}
		return eventEntity;

	}

	//사건에 첨부된 파일을 파일 테이블에 저장
	public static EventFileEntity eventFileUpload(EventEntity eventEntity, String eventFileName) {

		EventFileEntity eventFileEntity = new EventFileEntity();

		eventFileEntity.setFileJoinEvent(eventEntity);
		eventFileEntity.setEventFileName(eventFileName);

		return eventFileEntity;

	}

	//사건 Entity -> Dto
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

	//사건 분류 Entity -> Dto
	//사건 등록 시 분류를 선택하기 위함
	public static EventGroupDto eventGroupEntityToDto(EventGroupEntity eventGroupEntity) {

		EventGroupDto eventGroupDto = new EventGroupDto();

		eventGroupDto.setEventGroupId(eventGroupEntity.getEventGroupId());
		eventGroupDto.setEventGroupName(eventGroupEntity.getEventGroupName());

		return eventGroupDto;

	}

}
