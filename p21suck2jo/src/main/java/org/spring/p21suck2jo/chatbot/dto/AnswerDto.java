package org.spring.chatbot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.spring.chatbot.entity.AnswerEntity;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {

	private Long answerId;

	private String content;

	private String keyword;

	private String phone;

	public AnswerDto onlyPhone(SecondAnswer secondAnswer){
		this.phone = phone;
		return this;
	}

}
