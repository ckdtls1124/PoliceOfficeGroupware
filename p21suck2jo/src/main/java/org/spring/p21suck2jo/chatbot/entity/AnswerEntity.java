package org.spring.chatbot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.spring.chatbot.dto.AnswerDto;

import javax.persistence.*;
import javax.validation.constraints.Null;

@Entity
@Getter
@Builder
@Table(name = "chat_answer")
@AllArgsConstructor
@NoArgsConstructor
public class AnswerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long answerId;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private String keyword;

	public AnswerEntity keyword(String keyword){
		this.keyword = keyword;
		return this;
	}

	public AnswerDto answerDto(){
		return AnswerDto.builder()
						.answerId(answerId).content(content).keyword(keyword)
						.build();
	}

}
