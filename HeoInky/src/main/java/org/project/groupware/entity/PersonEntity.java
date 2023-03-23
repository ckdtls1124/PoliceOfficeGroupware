package org.project.groupware.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@Table(name = "person")
public class PersonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
	private Long personId;

	@Column(nullable = false)
	private String personName;

	private String personEmail;
	private int personPhone;
	private String personAddress;

	@OneToMany(mappedBy = "eventJoinPerson", cascade = CascadeType.ALL)
	private List<EventEntity> eventList = new ArrayList<>();

}
