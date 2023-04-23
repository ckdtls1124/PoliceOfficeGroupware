package org.spring.p21suck2jo.chatbot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Table(name = "chat_officer")
@AllArgsConstructor
@NoArgsConstructor
public class OfficerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long officerId;

	@Column(nullable = false)
	private String officerName;

	@Column(nullable = false)
	private String officerPhone;

	@JoinColumn(name = "dept_id")
	@ManyToOne
	private ChatDeptEntity deptId;

}
