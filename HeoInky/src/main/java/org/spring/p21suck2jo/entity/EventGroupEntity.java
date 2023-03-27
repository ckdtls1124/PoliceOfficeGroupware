package org.spring.p21suck2jo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@Table(name = "eventGroup")
public class EventGroupEntity {
//사건 분류 엔티티

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	public Long eventGroupId;

	//사건 분류 이름(음주운전, 불법노점상, 무단투기 etc)
	@Column(nullable = false, unique = true)
	private String eventGroupName;
	
	//사건과 1:N 관계
	//하나의 사건 분류 그룹이 여러 개의 사건을 가진다
	@OneToMany(mappedBy = "eventJoinGroup")
	private List<EventEntity> eventList = new ArrayList<>();

}
