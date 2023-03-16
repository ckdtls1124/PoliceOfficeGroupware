package org.project.groupware.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "eventGroup_tb")
public class EventGroupEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	public Long eventGroup_id;


	@Column(nullable = false, unique = true)
	private String eventGroupName;

}
