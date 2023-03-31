package org.spring.p21suck2jo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "eventFile")
@AllArgsConstructor
@NoArgsConstructor
public class EventFileEntity extends BaseEntity {
//사건 현장 파일(이미지) 엔티티

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long eventFile_id;

	private String eventFileName;

	@ManyToOne
	@JoinColumn(name = "event_id")
	private EventEntity fileJoinEvent;


}
