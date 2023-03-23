package org.project.groupware.dto;

import lombok.*;
import org.project.groupware.entity.*;
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

//--------------순수 사건 관련 Dto------------
	public Long eventId;

	private int eventNumber;

	private String eventLocation;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime eventDate;

	private int eventSettle;

	private String eventNote;

//---------------파일 관련 Dto----------------
	private int eventAttachFile;

	private String eventFileName;

	private MultipartFile eventFile;

//-----------사건 분류 그룹 관련 Dto------------
	private Long eventGroup;

	private EventGroupEntity eventJoinGroup;

	private String eventGroupName;

//-----------------부서 관련 Dto-----------------
	private Long dept;

	private DeptEntity eventJoinDept;

	private String deptName;

//------------회원(경찰관) 관련 Dto-----------------
	private Long police;

	private PoliceEntity eventJoinPolice;

	private String policeName;

//------------------시민 관련 Dto--------------------
	private Long person;

	private PersonEntity eventJoinPerson;

	private String personName;

}
