package org.spring.groupware.chatbot.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SecondAnswer {

	private String deptName;

	private String officerName;

	private String officerPhone;

}
