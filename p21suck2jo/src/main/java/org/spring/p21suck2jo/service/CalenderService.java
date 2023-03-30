package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;

import org.spring.p21suck2jo.dto.CalenderDto;
import org.spring.p21suck2jo.entity.CalendarEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.CalendarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalenderService {

  private final CalendarRepository calendarRepository;

  public List<CalenderDto> eventListAll() {

    List<CalenderDto> calenderDtos = new ArrayList<>();
    List<CalendarEntity> calendarEntities = calendarRepository.findAll();

    for (CalendarEntity entity : calendarEntities) {
      CalenderDto calenderDto = CalenderDto.builder()
              .id(entity.getId())
              .start(entity.getStart())
              .content(entity.getContent())
              .end(entity.getEnd())
              .build();
      calenderDtos.add(calenderDto);
    }
    return calenderDtos;
  }


  public void setCalendar(CalenderDto calenderDto, Long sessionPoliceIdLong) {

    // 경찰 Entity를 만들어서 CalenderEntity에 주입
    PoliceEntity policeEntity = new PoliceEntity();
    policeEntity.setPoliceId(sessionPoliceIdLong);


    CalendarEntity entity=CalendarEntity
            .builder()
            .content(calenderDto.getContent())
            .start(calenderDto.getStart())
            .end(calenderDto.getEnd())
            .police(policeEntity)
            .build();

    CalendarEntity calendarEntity=calendarRepository.save(entity);

  }
}