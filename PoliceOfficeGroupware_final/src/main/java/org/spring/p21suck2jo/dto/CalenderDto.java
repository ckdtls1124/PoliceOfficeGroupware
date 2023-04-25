package org.spring.p21suck2jo.dto;

import lombok.*;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalenderDto {

  private Integer id;

  private String content;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private Date start;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private Date end;

  private PoliceEntity police;

  private Long policeId;

}
