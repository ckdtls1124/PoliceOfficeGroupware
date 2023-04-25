package org.spring.groupware.dto;

import lombok.*;
import org.spring.groupware.entity.PoliceEntity;
import org.springframework.format.annotation.DateTimeFormat;

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
