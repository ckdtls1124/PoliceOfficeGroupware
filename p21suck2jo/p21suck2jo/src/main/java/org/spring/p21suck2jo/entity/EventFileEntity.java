package org.spring.p21suck2jo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "eventFile_tb")
@AllArgsConstructor
@NoArgsConstructor
public class EventFileEntity extends BaseEntity {
//사건 현장 파일(이미지) 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long eventFile_id;

    private String eventFileOriginName;

    private String eventFileNewName;

    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "eventFileEntities")
=======
    @JoinColumn(name = "event_id")
>>>>>>> 7f258f5a50dc7c24595ef8ae19017e80b2c680db
    private EventEntity fileJoinEvent;

}
