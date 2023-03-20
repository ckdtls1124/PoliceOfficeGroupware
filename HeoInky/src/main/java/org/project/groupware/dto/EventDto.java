package org.project.groupware.dto;

import lombok.*;
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

	public Long event_id;

	private int eventNumber;

	@NotBlank(message = "발생 장소를 작성하시오")
	private String eventLocation;

	@Past
	@NotEmpty(message = "발생 일시를 입력하시오")
	private LocalDateTime eventDate;

	@NotEmpty(message = "해결 여부를 체크하시오")
	private int eventSettle;
	
	private String eventNote;

	private int eventAttachFile;

	private MultipartFile eventFile;

}
