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

	private SecondAnswer secondAnswer;

	public AnswerDto phone(SecondAnswer secondAnswer){
		this.secondAnswer = secondAnswer;
		return this;
	}

}
