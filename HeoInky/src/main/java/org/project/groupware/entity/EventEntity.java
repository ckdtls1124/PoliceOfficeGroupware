package org.project.groupware.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "policeEvent_tb")
public class EventEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eventId")
	public Long event_id;

	@Column(nullable = false)
	private Long eventNumber;

	@Column(nullable = false)
	private String eventLocation;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private LocalDate eventDate;

	@Column(nullable = false)
	private int eventSettle;

	@OneToOne(mappedBy = "EventGroupEntity")
	@JoinColumn(name = "eventGroupName")
	private String eventGroup;


//  사원(경찰)과 조인
//  @ManyToOne
//  @JoinColumn(name = "")
//  private officerEntity officerJoinEvent;

//  시민과 조인
//  @ManyToOne
//  @JoinColumn(name = "")
//  private citizenEntity citizenJoinEvent;

}
