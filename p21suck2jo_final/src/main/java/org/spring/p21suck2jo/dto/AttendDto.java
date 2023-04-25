package org.spring.p21suck2jo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.spring.p21suck2jo.entity.PoliceEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendDto {

    private Long attendId;

    private LocalDateTime checkTime;

    private LocalDateTime leaveTime;


    private PoliceEntity police;
}
