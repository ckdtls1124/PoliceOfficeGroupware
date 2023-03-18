package org.spring.p21suck2jo.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event_tb")
public class EventEntity {
//사건 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventId")
    public Long event_id;

    //사건 넘버
    @Column(nullable = false)
    private Long eventNumber;

    //사건 발생 장소
    @Column(nullable = false)
    private String eventLocation;

    //사건 발생 일시
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private LocalDateTime eventDate;

    //사건 해결 여부(해결 = 1, 미해결 = 0)
    @Column(nullable = false)
    private int eventSettle;

    //사건 정보(비고)
    @Column(nullable = false, length = 5000)
    private String eventNote;

    //사건 현장 파일과 1:N 관계
    //사건은 삭제될 수 없기에 cascade, orphanRemoval은 따로 설정하지 않는다
    @OneToMany(mappedBy = "fileJoinEvent")
    private List<EventFileEntity> eventFileEntities = new ArrayList<>();

    //사건 분류 그룹과 N:1 관계
    @ManyToOne
    @JoinColumn(name = "eventGroup_id")
    private EventGroupEntity eventGroup;

    //부서와 N:1 관계(추가해야함)

    //시민과 N:1 관계(추가해야함)

}
