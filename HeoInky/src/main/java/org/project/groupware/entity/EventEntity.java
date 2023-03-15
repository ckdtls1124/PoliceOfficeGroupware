package org.project.groupware.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "policeEvent")
public class EventEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "eventId")
  public Long event_id;

  private String eventGroup;

  private String eventContent;

  private int settle;


//  사원(경찰)과 조인
//  @ManyToOne
//  @JoinColumn(name = "")
//  private officerEntity officerJoinEvent;

//  시민과 조인
//  @ManyToOne
//  @JoinColumn(name = "")
//  private citizenEntity citizenJoinEvent;

}
