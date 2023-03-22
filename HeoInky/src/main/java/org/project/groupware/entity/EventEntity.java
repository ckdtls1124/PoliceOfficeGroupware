package org.project.groupware.entity;

import lombok.*;
import org.project.groupware.dto.EventDto;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event_tb")
public class EventEntity {
//사건 엔티티

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eventId")
	public Long eventId;

	//사건 넘버
	@Column(nullable = false)
	private int eventNumber;

	//사건 발생 장소
	@Column(nullable = false)
	private String eventLocation;

	//사건 발생 일시
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(nullable = false)
	private LocalDateTime eventDate;

	//사건 해결 여부(해결 = 1, 미해결 = 0)
	@Column(nullable = false)
	private int eventSettle;

	//사건 정보(비고)
	@Column(nullable = false, length = 5000)
	private String eventNote;

	//파일 첨부 여부(첨부 = 1, 미첨부 = 0)
	@Column(nullable = false)
	private int eventAttachFile;

	//사건 현장 파일과 1:N 관계
	//사건은 삭제될 수 없기에 cascade, orphanRemoval은 따로 설정하지 않는다
	@OneToMany(mappedBy = "fileJoinEvent")
	private List<EventFileEntity> eventFileEntities = new ArrayList<>();

	//사건 분류 그룹과 N:1 관계
	@ManyToOne
	@JoinColumn(name = "eventGroup_id")
	private EventGroupEntity eventJoinGroup;

	//부서와 N:1 관계

	//경찰관(사원)과 N:1 관계

	//시민과 N:1 관계

	
	//생성자
	public static EventEntity eventDtoToEntityFile(EventDto eventDto) {
		//첨부된 파일이 있을 때
		EventEntity eventEntity = new EventEntity();

		eventEntity.setEventNumber(new Random().nextInt(1000000000));
		eventEntity.setEventLocation(eventDto.getEventLocation());
		eventEntity.setEventDate(eventDto.getEventDate());
		eventEntity.setEventSettle(eventDto.getEventSettle());
		eventEntity.setEventNote(eventDto.getEventNote());
		eventEntity.setEventJoinGroup(eventDto.getEventJoinGroup());
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
		eventEntity.setEventAttachFile(0);

		return eventEntity;

	}

}
