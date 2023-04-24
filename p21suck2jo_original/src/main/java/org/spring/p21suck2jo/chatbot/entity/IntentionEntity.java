package org.spring.p21suck2jo.chatbot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Table(name = "chat_intention")
@AllArgsConstructor
@NoArgsConstructor
public class IntentionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long intentId;

	@Column(nullable = false)
	private String keyword;

	@JoinColumn(name = "answer_id")
	@ManyToOne
	private AnswerEntity answerId;

	@JoinColumn
	@ManyToOne
	private IntentionEntity upperNo;

}
