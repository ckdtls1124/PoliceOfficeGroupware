package org.spring.p21suck2jo.constructor;

import org.spring.p21suck2jo.dto.EventDto;
import org.spring.p21suck2jo.dto.EventGroupDto;
import org.spring.p21suck2jo.entity.EventEntity;
import org.spring.p21suck2jo.entity.EventFileEntity;
import org.spring.p21suck2jo.entity.EventGroupEntity;

import java.util.Random;

public class EventConstructors {

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

	public static EventGroupDto eventGroupEntityToDto(EventGroupEntity eventGroupEntity) {

		EventGroupDto eventGroupDto = new EventGroupDto();

		eventGroupDto.setEventGroupId(eventGroupEntity.getEventGroupId());
		eventGroupDto.setEventGroupName(eventGroupEntity.getEventGroupName());

		return eventGroupDto;
	}

}
