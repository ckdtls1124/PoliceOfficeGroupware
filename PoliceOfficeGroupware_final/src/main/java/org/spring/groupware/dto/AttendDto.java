package org.spring.groupware.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.spring.groupware.entity.PoliceEntity;

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
