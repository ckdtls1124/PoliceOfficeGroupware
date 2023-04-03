package org.spring.p21suck2jo.chatbot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {

	private Long answerId;

	private String content;

	private String keyword;

	private String phone;

	public AnswerDto phone(SecondAnswer secondAnswer){
		this.phone = phone;
		return this;
	}

}
