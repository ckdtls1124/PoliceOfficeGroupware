package org.spring.p21suck2jo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "attend")
public class AttendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attend_id")
    private Long attendId;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime checkTime;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime leaveTime;


    @ManyToOne
    @JoinColumn(name = "police_id")
    private PoliceEntity police;

}
