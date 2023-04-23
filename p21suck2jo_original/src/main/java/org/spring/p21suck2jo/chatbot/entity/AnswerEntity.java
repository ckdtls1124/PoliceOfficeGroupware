package org.spring.p21suck2jo.chatbot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.spring.p21suck2jo.chatbot.dto.AnswerDto;

import javax.persistence.*;

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
