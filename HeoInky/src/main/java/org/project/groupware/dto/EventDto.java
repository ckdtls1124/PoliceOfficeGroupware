package org.project.groupware.dto;

import lombok.*;
import org.project.groupware.entity.EventEntity;
import org.project.groupware.entity.EventGroupEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

	public Long eventId;

	private int eventNumber;

	private String eventLocation;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime eventDate;

	private int eventSettle;

	private String eventNote;

	private int eventAttachFile;

	private String eventFileName;

	private MultipartFile eventFile;

	private Long eventGroup;

	private EventGroupEntity eventJoinGroup;

	private String eventGroupName;

	public static EventDto eventEntityToDto(EventEntity eventEntity) {

		EventDto eventDto = new EventDto();

		eventDto.setEventId(eventEntity.getEventId());
		eventDto.setEventNumber(eventEntity.getEventNumber());
		eventDto.setEventLocation(eventEntity.getEventLocation());
		eventDto.setEventDate(eventEntity.getEventDate());
		eventDto.setEventSettle(eventEntity.getEventSettle());
		eventDto.setEventNote(eventEntity.getEventNote());
		eventDto.setEventGroupName(eventEntity.getEventJoinGroup().getEventGroupName());

		if(eventEntity.getEventAttachFile()==0){
			eventDto.setEventAttachFile(eventDto.getEventAttachFile());
		}else {
			eventDto.setEventAttachFile(eventDto.getEventAttachFile());
			eventDto.setEventFileName(eventEntity.getEventFileEntities().get(0).getEventFileName());
		}

		return eventDto;
	}

}
