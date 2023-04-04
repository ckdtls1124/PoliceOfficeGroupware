package org.spring.p21suck2jo.chatbot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

	private String today;

	private String time;

	private AnswerDto answer;

	public MessageDto today(String today) {
		this.today = today;
		return this;
	}

	public MessageDto answer(AnswerDto answer) {
		this.answer = answer;
		return this;
	}

}
