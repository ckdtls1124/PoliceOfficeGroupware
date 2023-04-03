package org.spring.chatbot.entity;

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
	private Long intenId;

	@Column(nullable = false)
	private String keyword;

	@JoinColumn(name = "answer_id")
	@ManyToOne
	private AnswerEntity answerId;

	@JoinColumn(name = "inten_id")
	@ManyToOne
	private IntentionEntity upperNo;

}
