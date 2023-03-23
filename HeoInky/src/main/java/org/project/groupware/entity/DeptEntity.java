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
@Table(name = "dept")
public class DeptEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dept_id")
	private Long deptId;

	private String deptName;
	private String deptLocation;//부서위치

	@OneToMany(mappedBy = "dept", cascade = CascadeType.ALL)
	List<PoliceEntity> policeList = new ArrayList<>();

	@OneToMany(mappedBy = "eventJoinDept")
	List<EventEntity> eventList = new ArrayList<>();

}
