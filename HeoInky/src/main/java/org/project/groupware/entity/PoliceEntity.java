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
@Table(name = "police_officer")
public class PoliceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "police_id")
	private Long policeId;

	private String policeName;

	@ManyToOne
	@JoinColumn(name ="dept_id")
	private DeptEntity dept;

	@OneToMany(mappedBy = "eventJoinPolice", cascade = CascadeType.ALL)
	private List<EventEntity> EventList = new ArrayList<>();

}
