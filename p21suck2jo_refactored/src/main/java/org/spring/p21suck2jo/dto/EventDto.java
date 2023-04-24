package org.spring.p21suck2jo.dto;

import lombok.*;
import org.spring.p21suck2jo.entity.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

//-------------------순수 사건 관련 Dto-------------------
	public Long eventId;

	private int eventNumber;

	@NotBlank(message = "※장소를 입력하시오.")
	private String eventLocation;

	@NotNull(message = "※일시를 선택하시오.")
	@PastOrPresent(message = "※일시를 다시 확인하시오.")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime eventDate;

	@NotNull(message = "※해결 여부를 선택하시오.")
	private int eventSettle;

	private String eventNote;

//-------------------파일 관련 Dto-------------------
	private int eventAttachFile;

	private String eventFileName;

	private MultipartFile eventFile;

//-------------------사건 분류 그룹 관련 Dto-------------------
	private Long eventGroup;

	private EventGroupEntity eventJoinGroup;

	private String eventGroupName;

//-------------------부서 관련 Dto-------------------
	private Long dept;

	private DeptEntity eventJoinDept;

	private String deptName;

//-------------------회원(경찰관) 관련 Dto-------------------
	private Long police;

	private PoliceEntity eventJoinPolice;

	private String policeName;

//-------------------시민 관련 Dto-------------------
	private Long person;

	private PersonEntity eventJoinPerson;

	private String personName;

}
